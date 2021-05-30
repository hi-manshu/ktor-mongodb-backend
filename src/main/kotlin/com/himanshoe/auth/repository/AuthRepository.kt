package com.himanshoe.auth.repository

import com.himanshoe.auth.AuthRequest
import com.himanshoe.util.BaseResponse

interface AuthRepository {

    suspend fun createToken(authRequest: AuthRequest): BaseResponse<Any>

    suspend fun loginUser(authRequest: AuthRequest): BaseResponse<Any>

}