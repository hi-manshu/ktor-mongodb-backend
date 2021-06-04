package com.himanshoe.feature.user.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.feature.user.User
import com.himanshoe.feature.user.repository.UserRepository
import com.himanshoe.util.BaseResponse

class UpdateCurrentUserUseCase(private val userRepository: UserRepository) : BaseUseCase<Pair<String, User>, Any> {

    override suspend fun invoke(input: Pair<String, User>): BaseResponse<Any> {
        return userRepository.updateUser(input.first, input.second)
    }
}
