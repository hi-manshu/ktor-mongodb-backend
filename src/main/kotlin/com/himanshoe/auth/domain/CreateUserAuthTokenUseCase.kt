package com.himanshoe.auth.domain

import com.himanshoe.auth.AuthRequest
import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.util.BaseResponse

class CreateUserAuthTokenUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(authRequest: AuthRequest): BaseResponse<Any> {
        return authRepository.loginUser(authRequest)
    }
}