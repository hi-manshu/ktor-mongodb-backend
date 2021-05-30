package com.himanshoe.user.repository

import com.himanshoe.util.BaseResponse

interface UserRepository {

    suspend fun findUserById(userId: String?): BaseResponse<Any>

    suspend fun currentUser(userId: String?): BaseResponse<Any>

}