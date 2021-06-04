package com.himanshoe.feature.auth.domain

import com.himanshoe.feature.auth.AuthRequest
import com.himanshoe.feature.auth.repository.AuthRepository
import com.himanshoe.base.BaseUseCase
import com.himanshoe.util.BaseResponse

/**
 * [CreateUserAuthTokenUseCase] is the UseCase which overrides [BaseUseCase]
 * and invokes to generate UserToken from [AuthRepository]
 */
class CreateUserAuthTokenUseCase(private val authRepository: AuthRepository) : BaseUseCase<AuthRequest, Any> {

    /**
     * [invoke] gets called when the UseCase in instantiated
     */
    override suspend fun invoke(input: AuthRequest): BaseResponse<Any> {
        return authRepository.createToken(input)
    }
}
