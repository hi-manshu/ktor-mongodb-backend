package com.himanshoe.feature.comment.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.feature.comment.repository.CommentRepository
import com.himanshoe.util.BaseResponse

class GetCommentByIdUseCase(private val commentRepository: CommentRepository) : BaseUseCase<String, Any> {
    /** Executes this use case with given input. */
    override suspend fun invoke(input: String): BaseResponse<Any> {
        return commentRepository.findCommentById(input)
    }
}
