package com.himanshoe.feature.user.service

import com.himanshoe.feature.user.User

interface UserApiService {

    suspend fun populate(userIds: List<String>): List<User>

    suspend fun insertUser(user: User): Boolean

    suspend fun findUserByUsername(username: String): User?

    suspend fun findUserByUserId(userId: String): User?

    suspend fun updateUserById(userId: String, user: User): Boolean?
}
