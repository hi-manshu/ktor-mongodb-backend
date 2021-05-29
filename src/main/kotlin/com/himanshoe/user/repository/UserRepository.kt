package com.himanshoe.user.repository

import com.himanshoe.user.User
import com.himanshoe.util.BaseResponse

interface UserRepository {

    suspend fun findUserById(userId: String?): BaseResponse<User>

}