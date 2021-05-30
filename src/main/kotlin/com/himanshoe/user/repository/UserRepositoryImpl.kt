package com.himanshoe.user.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.user.User
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.Logger
import io.ktor.http.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class UserRepositoryImpl(
    private val userCollection: CoroutineCollection<User>,
    private val exceptionHandler: ExceptionHandler
) : UserRepository {

    companion object {
        private const val USER_NOT_FOUND = "User not found"
    }

    override suspend fun findUserById(userId: String?): BaseResponse<User> {
        val (user, doExist) = checkIfUsersExistWithUserData(userId)
        if (userId != null && doExist) {
            return BaseResponse(HttpStatusCode.Found, user)
        } else {
            throw exceptionHandler.respondWithNotFoundException(USER_NOT_FOUND)
        }
    }

    private suspend fun checkIfUsersExistWithUserData(userId: String?): Pair<User?, Boolean> {
        val user = userCollection.findOne(User::userId eq userId.toString())
        Logger.d(user.toString())
        Logger.d(userId.toString())
        return Pair(user, user != null)
    }
}