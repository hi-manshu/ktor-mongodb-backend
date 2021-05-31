package com.himanshoe.posts.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.posts.Post
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.Logger
import com.himanshoe.util.PaginatedResponse
import com.himanshoe.util.SuccessResponse
import io.ktor.http.*
import org.litote.kmongo.coroutine.CoroutineCollection
import java.util.*

class PostsRepositoryImpl(
    private val postCollection: CoroutineCollection<Post>,
    private val exceptionHandler: ExceptionHandler
) : PostsRepository {

    companion object {
        private const val PLEASE_CHECK_THE_PARAMS = "Please check the query params"
        private const val NOT_AUTHORIZED = "Not authorised"
        private const val ZERO = 0
        private const val ONE = 1
    }

    override suspend fun fetchPosts(page: Int, count: Int): BaseResponse<Any> {
        if (page > ZERO && count > ZERO) {
            val skips = page.minus(ONE) * count
            val response = postCollection.find().skip(skips).limit(count)
            val results: List<Post> = response.toList().sortedBy {
                it.createdAt
            }
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
            Logger.d(post)
            Logger.d(userId)
            val postToBeCreated = Post(title = post.title, post = post.post).copy(
                createdBy = userId,
                createdAt = Date().toInstant().toString(),
                updatedAt = Date().toInstant().toString()
            )
            val isCreated = postCollection.insertOne(postToBeCreated).wasAcknowledged()

            if (isCreated) {
                return SuccessResponse(statusCode = HttpStatusCode.Created, postToBeCreated)
            } else {
                throw exceptionHandler.respondWithSomethingWentWrongException()
            }
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(NOT_AUTHORIZED)
        }
    }

}