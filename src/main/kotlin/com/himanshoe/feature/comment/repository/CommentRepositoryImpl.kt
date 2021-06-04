package com.himanshoe.feature.comment.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.feature.comment.Comment
import com.himanshoe.feature.comment.service.CommentApiService
import com.himanshoe.util.BaseResponse

class CommentRepositoryImpl(
    private val commentApiService: CommentApiService,
    private val exceptionHandler: ExceptionHandler
) : CommentRepository {
    override suspend fun fetchComments(page: Int, count: Int): BaseResponse<Any> {
    }

    override suspend fun createComment(userId: String?, postId: String, comment: Comment): BaseResponse<Any> {
    }

    override suspend fun findPostById(commentId: String?): BaseResponse<Any> {
    }
}
