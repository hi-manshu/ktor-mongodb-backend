package com.himanshoe.posts.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.posts.Post
import com.himanshoe.user.User
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.PaginatedResponse
import com.himanshoe.util.SuccessResponse
import io.ktor.http.*
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.projection
import org.litote.kmongo.exclude
import org.litote.kmongo.fields
import java.util.*

class PostsRepositoryImpl(
    private val postCollection: CoroutineCollection<Post>,
    private val userCollection: CoroutineCollection<User>,
    private val exceptionHandler: ExceptionHandler
) : PostsRepository {

    companion object {
        private const val PLEASE_CHECK_THE_PARAMS = "Please check the query params"
        private const val NOT_AUTHORIZED = "Not authorised"
        private const val ALREADY_LIKED = "Already liked"
        private const val ZERO = 0
        private const val ONE = 1
    }

    override suspend fun fetchPosts(page: Int, count: Int): BaseResponse<Any> {
        if (page > ZERO && count > ZERO) {
            val skips = page.minus(ONE) * count
            val response = postCollection.find().skip(skips).limit(count)
            val results: List<Post> = response.toList().sortedBy {
                it.createdAt
            }.map {
                it.asResponse()
            }
            val listOfUserIds: List<String> = results.map { it.likes.map { it } }.flatten()
            val totalCount = postCollection.estimatedDocumentCount().toInt()
            val totalPages = (totalCount.div(count)).plus(ONE)
            val next = if (results.isNotEmpty()) page.plus(ONE) else null
            val prev = if (page > ZERO) page.minus(ONE) else null

            return PaginatedResponse(statusCode = HttpStatusCode.OK, prev, next, totalCount, totalPages, results)
        } else {
            throw exceptionHandler.respondWithGenericException(PLEASE_CHECK_THE_PARAMS)
        }
    }

    override suspend fun createPost(userId: String?, post: Post): BaseResponse<Any> {
        if (userId != null) {
            val postToBeCreated = Post(title = post.title, post = post.post).copy(
                createdBy = userId,
                createdAt = Date().toInstant().toString(),
                updatedAt = Date().toInstant().toString()
            )
            val isCreated = postCollection.insertOne(postToBeCreated).wasAcknowledged()

            if (isCreated) {
                return SuccessResponse(statusCode = HttpStatusCode.Created, postToBeCreated.asResponse())
            } else {
                throw exceptionHandler.respondWithSomethingWentWrongException()
            }
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(NOT_AUTHORIZED)
        }
    }

    override suspend fun likeDislikePost(userId: String?, postId: String?, isLiked: Boolean): BaseResponse<Any> {
        val post = checkIfPostExistWithPostData(postId)
        if (post.first?.postId == postId && post.second) {
            if (post.first?.likes?.contains(userId) == true) {
                if (isLiked) {
                    throw exceptionHandler.respondWithGenericException(ALREADY_LIKED)
                } else {
                    //Delete
                    throw exceptionHandler.respondWithSomethingWentWrongException()
                }
            } else {
                val likes: List<String> = post.first?.likes?.toMutableList().apply {
                    userId?.let { this?.add(it) }
                }?.toList() ?: emptyList()
                val postUpdated = post.first?.copy(
                    likes = likes
                )
                val isLiked: Boolean =
                    postId?.let { postUpdated?.let { post -> postCollection.updateOneById(it, post) } }
                        ?.wasAcknowledged() == true
                if (isLiked) {
                    return SuccessResponse(HttpStatusCode.OK, "Liked")
                } else {
                    throw exceptionHandler.respondWithSomethingWentWrongException()
                }
            }
        } else {
            throw exceptionHandler.respondWithSomethingWentWrongException()
        }
    }


    private suspend fun checkIfPostExistWithPostData(postId: String?): Pair<Post?, Boolean> {
        val post = postId?.let { postCollection.findOneById(it) }
        return Pair(post, post != null)
    }

}