package com.himanshoe.user.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.user.User
import com.himanshoe.user.repository.UserRepository
import com.himanshoe.util.BaseResponse

class UpdateCurrentUserUseCase(private val userRepository: UserRepository) : BaseUseCase<Pair<String, User>, Any> {

    override suspend fun invoke(input: Pair<String, User>): BaseResponse<Any> {
        return userRepository.updateUser(input.first, input.second)
    }
}