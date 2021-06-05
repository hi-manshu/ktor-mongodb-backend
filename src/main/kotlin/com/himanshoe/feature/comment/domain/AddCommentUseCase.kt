package com.himanshoe.feature.comment.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.feature.comment.request.AddCommentRequest
import com.himanshoe.feature.comment.repository.CommentRepository
import com.himanshoe.util.BaseResponse

class AddCommentUseCase(private val commentRepository: CommentRepository) : BaseUseCase<AddCommentRequest, Any> {
    /** Executes this use case with given input. */
    override suspend fun invoke(input: AddCommentRequest): BaseResponse<Any> {
        return commentRepository.addComment(input.userId, input.postId, input.comment)
    }
}
