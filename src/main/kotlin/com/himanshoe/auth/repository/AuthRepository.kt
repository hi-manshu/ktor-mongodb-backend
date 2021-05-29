package com.himanshoe.auth.repository

import com.himanshoe.auth.AuthRequest

interface AuthRepository {

    suspend fun createToken(authRequest: AuthRequest): String

    suspend fun checkIfUsersExist(username: String): Boolean
}