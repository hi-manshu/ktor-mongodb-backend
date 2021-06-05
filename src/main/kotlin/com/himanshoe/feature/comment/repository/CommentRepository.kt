package com.himanshoe.feature.comment.repository

import com.himanshoe.feature.comment.Comment
import com.himanshoe.util.BaseResponse

interface CommentRepository {

    suspend fun fetchComments(page: Int, count: Int): BaseResponse<Any>

    suspend fun addComment(userId: String?, postId: String, comment: Comment): BaseResponse<Any>

    suspend fun findCommentById(commentId: String?): BaseResponse<Any>
}
