package com.himanshoe.auth.domain

import com.himanshoe.auth.AuthRequest
import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.base.BaseUseCase
import com.himanshoe.util.BaseResponse

class LoginUserUseCase(private val authRepository: AuthRepository) : BaseUseCase<AuthRequest, Any> {

    override suspend fun invoke(input: AuthRequest): BaseResponse<Any> {
        return authRepository.loginUser(input)
    }
}