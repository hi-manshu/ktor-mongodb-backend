package com.himanshoe.feature.comment.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.feature.comment.request.AddCommentRequest
import com.himanshoe.feature.comment.repository.CommentRepository
import com.himanshoe.feature.comment.request.FetchCommentsRequest
import com.himanshoe.util.BaseResponse

class GetCommentsUseCase(private val commentRepository: CommentRepository) : BaseUseCase<FetchCommentsRequest, Any> {
    /** Executes this use case with given input. */
    override suspend fun invoke(input: FetchCommentsRequest): BaseResponse<Any> {
        return commentRepository.fetchComments(input.page,input.count)
    }
}
