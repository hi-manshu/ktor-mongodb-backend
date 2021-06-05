package com.himanshoe.feature.user.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.feature.user.repository.UserRepository
import com.himanshoe.util.BaseResponse

class GetUserPostsUseCase(private val userRepository: UserRepository) : BaseUseCase<String, Any> {

    override suspend fun invoke(input: String): BaseResponse<Any> {
        return userRepository.fetchUserPosts(input)
    }
}
