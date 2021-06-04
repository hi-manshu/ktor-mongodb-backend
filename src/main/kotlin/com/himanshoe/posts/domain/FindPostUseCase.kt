package com.himanshoe.posts.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.posts.repository.PostsRepository
import com.himanshoe.util.BaseResponse


class FindPostUseCase(private val postsRepository: PostsRepository) : BaseUseCase<String, Any> {

    /** Executes this use case with given input. */
    override suspend fun invoke(input: String): BaseResponse<Any> {
        return postsRepository.findPostById(input)
    }
}