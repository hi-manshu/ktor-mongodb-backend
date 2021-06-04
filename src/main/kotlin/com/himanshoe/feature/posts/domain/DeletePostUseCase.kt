package com.himanshoe.feature.posts.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.util.BaseResponse

class DeletePostUseCase(private val postsRepository: PostsRepository) : BaseUseCase<Pair<String, String>, Any> {
    /** Executes this use case with given input. */
    override suspend fun invoke(input: Pair<String, String>): BaseResponse<Any> {
        return postsRepository.deletePost(input.first, input.second)
    }
}
