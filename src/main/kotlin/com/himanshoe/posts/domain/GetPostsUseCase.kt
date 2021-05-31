package com.himanshoe.posts.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.posts.repository.PostsRepository
import com.himanshoe.util.BaseResponse

class GetPostsUseCase(private val postsRepository: PostsRepository) : BaseUseCase<Pair<Int, Int>, Any> {

    /** Executes this use case with given input. */
    override suspend fun invoke(input: Pair<Int, Int>): BaseResponse<Any> {
        return postsRepository.fetchPosts(input.first,input.second)
    }
}