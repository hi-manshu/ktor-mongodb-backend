package com.himanshoe.user.repository

import com.himanshoe.user.User
import com.himanshoe.util.BaseResponse

interface UserRepository {

    suspend fun findUserById(userId: String?): BaseResponse<Any>

    suspend fun currentUser(userId: String?): BaseResponse<Any>

    suspend fun updateUser(userId:String,user:User): BaseResponse<Any>

}