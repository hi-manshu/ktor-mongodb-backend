package com.himanshoe.auth.repository

import com.himanshoe.auth.AuthRequest
import com.himanshoe.util.Response

interface AuthRepository {

    suspend fun createToken(authRequest: AuthRequest): Response<Any>

    suspend fun loginUser(authRequest: AuthRequest): Response<Any>

    suspend fun checkIfUsersExist(username: String): Boolean
}