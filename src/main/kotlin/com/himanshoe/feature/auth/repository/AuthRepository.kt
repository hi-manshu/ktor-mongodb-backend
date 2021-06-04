package com.himanshoe.feature.auth.repository

import com.himanshoe.feature.auth.AuthRequest
import com.himanshoe.util.BaseResponse

/**
 * [AuthRepository] is a collection of all the functions to Auth module
 */
interface AuthRepository {

    suspend fun createToken(authRequest: AuthRequest): BaseResponse<Any>

    suspend fun loginUser(authRequest: AuthRequest): BaseResponse<Any>
}
