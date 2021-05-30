package com.himanshoe.auth.domain

import com.himanshoe.auth.AuthRequest
import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.base.BaseUseCase
import com.himanshoe.util.BaseResponse

/**
 * [CreateUserAuthTokenUseCase] is the UseCase which overrides [BaseUseCase]
 * and invokes to generate UserToken from [AuthRepositorys]
 */
class CreateUserAuthTokenUseCase(private val authRepository: AuthRepository) : BaseUseCase<AuthRequest, Any> {

    /**
     * [invoke] gets called when the UseCase in instantiated
     */
    override suspend fun invoke(input: AuthRequest): BaseResponse<Any> {
        return authRepository.createToken(input)
    }
}