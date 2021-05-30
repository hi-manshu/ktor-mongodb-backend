package com.himanshoe.user.domain

import com.himanshoe.base.BaseUseCase
import com.himanshoe.user.repository.UserRepository
import com.himanshoe.util.BaseResponse

class FindUserByIdUseCase(private val userRepository: UserRepository) : BaseUseCase<String, Any> {

    override suspend fun invoke(input: String): BaseResponse<Any> {
        return userRepository.findUserById(input)
    }
}