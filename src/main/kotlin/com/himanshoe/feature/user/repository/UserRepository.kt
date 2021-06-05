package com.himanshoe.feature.user.repository

import com.himanshoe.feature.user.User
import com.himanshoe.util.BaseResponse

interface UserRepository {

    suspend fun findUserById(userId: String?): BaseResponse<Any>

    suspend fun currentUser(userId: String?): BaseResponse<Any>

    suspend fun updateUser(userId: String, user: User): BaseResponse<Any>

    suspend fun fetchUserPosts(userId: String): BaseResponse<Any>
}
