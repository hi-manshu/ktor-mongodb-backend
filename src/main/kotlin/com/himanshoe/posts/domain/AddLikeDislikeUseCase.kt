package com.himanshoe.posts.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.posts.repository.PostsRepository
import com.himanshoe.util.BaseResponse

class AddLikeDislikeUseCase(private val postsRepository: PostsRepository) :
    BaseUseCase<Triple<String, String, Boolean>, Any> {
    /** Executes this use case with given input. */
    override suspend fun invoke(input: Triple<String, String, Boolean>): BaseResponse<Any> {
        return postsRepository.likeDislikePost(input.first, input.second, input.third)
    }
}
