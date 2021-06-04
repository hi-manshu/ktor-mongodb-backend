package com.himanshoe.feature.posts.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.feature.posts.Post
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.util.BaseResponse

class CreatePostUseCase(private val postsRepository: PostsRepository) : BaseUseCase<Pair<String?, Post>, Any> {

    /** Executes this use case with given input. */
    override suspend fun invoke(input: Pair<String?, Post>): BaseResponse<Any> {
        return postsRepository.createPost(input.first, input.second)
    }
}
