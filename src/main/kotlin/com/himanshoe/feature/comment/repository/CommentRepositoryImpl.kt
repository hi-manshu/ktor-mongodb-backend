package com.himanshoe.feature.comment.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.feature.comment.Comment
import com.himanshoe.feature.comment.service.CommentApiService
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.PaginatedResponse
import com.himanshoe.util.SuccessResponse
import io.ktor.http.*

class CommentRepositoryImpl(
    private val commentApiService: CommentApiService,
    private val exceptionHandler: ExceptionHandler
) : CommentRepository {

    companion object {
        private const val ZERO = 0
        private const val NOT_AUTHORIZED = "Not authorised"
        private const val ONE = 1
        private const val COMMENT_NOT_FOUND = "Comment not found"
        private const val PLEASE_CHECK_THE_PARAMS = "Please check the query params"
    }

    override suspend fun fetchComments(page: Int, count: Int): BaseResponse<Any> {
        if (page > ZERO && count > ZERO) {
            val commentList: Pair<List<Comment>, Int> = commentApiService.fetchComments(page, count)
            val totalCount = commentList.second
            val totalPages = (totalCount.div(count)).plus(ONE)
            val next = if (commentList.first.isNotEmpty()) page.plus(ONE) else null
            val prev = if (page > ZERO) page.minus(ONE) else null
            return PaginatedResponse(
                statusCode = HttpStatusCode.OK,
                prev,
                next,
                totalCount,
                totalPages,
                commentList.first
            )
        } else {
            throw exceptionHandler.respondWithGenericException(PLEASE_CHECK_THE_PARAMS)
        }
    }

    override suspend fun addComment(userId: String?, postId: String, comment: Comment): BaseResponse<Any> {
        if (userId != null) {
            val isAdded = commentApiService.addComment(userId, postId, comment)
            if (isAdded) {
                return SuccessResponse(statusCode = HttpStatusCode.Created, isAdded)
            } else {
                throw exceptionHandler.respondWithSomethingWentWrongException()
            }
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(NOT_AUTHORIZED)
        }
    }

    override suspend fun findCommentById(commentId: String?): BaseResponse<Any> {
        val comment = commentApiService.findCommentById(commentId)
        if (comment != null) {
            return SuccessResponse(
                HttpStatusCode.OK,
                comment
            )
        } else {
            throw exceptionHandler.respondWithNotFoundException(COMMENT_NOT_FOUND)
        }
    }
}
