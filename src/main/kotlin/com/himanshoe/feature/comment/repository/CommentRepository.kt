package com.himanshoe.feature.comment.repository

import com.himanshoe.feature.comment.Comment
import com.himanshoe.util.BaseResponse

interface CommentRepository {

    suspend fun fetchComments(page: Int, count: Int): BaseResponse<Any>

    suspend fun createComment(userId: String?, postId: String, comment: Comment): BaseResponse<Any>

    suspend fun findPostById(commentId: String?): BaseResponse<Any>
}
