package com.himanshoe.feature.posts.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.feature.comment.service.CommentApiService
import com.himanshoe.feature.posts.Post
import com.himanshoe.feature.posts.PostList
import com.himanshoe.feature.posts.service.PostApiService
import com.himanshoe.feature.posts.toPostWithUser
import com.himanshoe.feature.posts.toPostWithUserDetails
import com.himanshoe.feature.user.service.UserApiService
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.PaginatedResponse
import com.himanshoe.util.SuccessResponse
import io.ktor.http.*
import java.util.*

class PostsRepositoryImpl(
    private val postApiService: PostApiService,
    private val userApiService: UserApiService,
    private val commentApiService: CommentApiService,
    private val exceptionHandler: ExceptionHandler
) : PostsRepository {

    companion object {
        private const val PLEASE_CHECK_THE_PARAMS = "Please check the query params"
        private const val NOT_AUTHORIZED = "Not authorised"
        private const val ALREADY_LIKED = "Already liked"
        private const val POST_NOT_FOUND = "Post not found"
        private const val ZERO = 0
        private const val ONE = 1
    }

    override suspend fun fetchPosts(page: Int, count: Int): BaseResponse<Any> {
        if (page > ZERO && count > ZERO) {

            val postList: Pair<List<Post>, Int> = postApiService.fetchPosts(page, count)

            val response: List<PostList> = postList.first.map {
                it.toPostWithUser(userApiService, postList.first)
            }

            val totalCount = postList.second
            val totalPages = (totalCount.div(count)).plus(ONE)
            val next = if (response.isNotEmpty()) page.plus(ONE) else null
            val prev = if (page > ZERO) page.minus(ONE) else null
            return PaginatedResponse(statusCode = HttpStatusCode.OK, prev, next, totalCount, totalPages, response)
        } else {
            throw exceptionHandler.respondWithGenericException(PLEASE_CHECK_THE_PARAMS)
        }
    }

    override suspend fun createPost(userId: String?, post: Post): BaseResponse<Any> {
        if (userId != null) {
            val isCreated = postApiService.createPost(userId, post)

            if (isCreated) {
                return SuccessResponse(statusCode = HttpStatusCode.Created, isCreated)
            } else {
                throw exceptionHandler.respondWithSomethingWentWrongException()
            }
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(NOT_AUTHORIZED)
        }
    }

    override suspend fun findPostById(postId: String?): BaseResponse<Any> {
        val post = postApiService.findPostById(postId)
        if (post != null) {
            return SuccessResponse(
                HttpStatusCode.OK,
                post.toPostWithUserDetails(userApiService, post.likes)
            )
        } else {
            throw exceptionHandler.respondWithNotFoundException(POST_NOT_FOUND)
        }
    }

    override suspend fun likeDislikePost(userId: String?, postId: String?, isLiked: Boolean): BaseResponse<Any> {
        val post = checkIfPostExistWithData(postId)
        return if (post.first?.postId == postId && post.second) {
            if (post.first?.likes?.contains(userId) == true) {
                if (isLiked) {
                    throw exceptionHandler.respondWithGenericException(ALREADY_LIKED)
                } else {
                    userDislikePost(post, userId, postId)
                }
            } else {
                userLikePost(post, userId, postId)
            }
        } else {
            throw exceptionHandler.respondWithSomethingWentWrongException()
        }
    }

    private suspend fun userDislikePost(
        post: Pair<Post?, Boolean>,
        userId: String?,
        postId: String?
    ): BaseResponse<Any> {
        val likes: List<String> = post.first?.likes?.toMutableList().apply {
            if (userId?.let { this?.contains(it) } == true) {
                this?.remove(userId)
            }
        }?.toList() ?: emptyList()
        return showLikeDislikeStatus(postId, likes)
    }

    private suspend fun showLikeDislikeStatus(postId: String?, likes: List<String>): BaseResponse<Any> {
        val isDislikedUpdated = postApiService.likeDislikePost(postId, likes)
        if (isDislikedUpdated == true) {
            return SuccessResponse(HttpStatusCode.OK, "Disliked Post")
        } else {
            throw exceptionHandler.respondWithSomethingWentWrongException()
        }
    }

    private suspend fun userLikePost(post: Pair<Post?, Boolean>, userId: String?, postId: String?): BaseResponse<Any> {
        val likes: List<String> = post.first?.likes?.toMutableList().apply {
            userId?.let { this?.add(it) }
        }?.toList() ?: emptyList()
        return showLikeDislikeStatus(postId, likes)
    }

    override suspend fun deletePost(userId: String?, postId: String?): BaseResponse<Any> {
        val postData = checkIfPostExistWithData(postId)
        val post = postData.first
        if (userId == post?.createdBy) {
            val response = postApiService.deletePost(postId)
            return SuccessResponse(HttpStatusCode.OK, response)
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(NOT_AUTHORIZED)
        }
    }

    private suspend fun checkIfPostExistWithData(postId: String?): Pair<Post?, Boolean> {
        val post = postApiService.findPostById(postId)
        return Pair(post, post != null)
    }
}
