package com.himanshoe.user.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.user.User
import com.himanshoe.util.BaseResponse
import io.ktor.http.*
import org.litote.kmongo.coroutine.CoroutineCollection

class UserRepositoryImpl(
    private val userCollection: CoroutineCollection<User>,
    private val exceptionHandler: ExceptionHandler
) : UserRepository {

    companion object {
        private const val USER_NOT_FOUND = "User not found"
        private const val TOKEN_NOT_FOUND = "Token cannot be null or empty"
    }

    override suspend fun findUserById(userId: String?): BaseResponse<Any> {
        val (user, doExist) = checkIfUsersExistWithUserData(userId)
        if (userId != null && doExist) {
            return BaseResponse(HttpStatusCode.Found, user?.asResponse())
        } else {
            throw exceptionHandler.respondWithNotFoundException(USER_NOT_FOUND)
        }
    }

    override suspend fun currentUser(userId: String?): BaseResponse<Any> {
        val (user, doExist) = checkIfUsersExistWithUserData(userId)
        if (userId != null && doExist) {
            return BaseResponse(HttpStatusCode.Found, user?.asResponse())
        } else {
            throw exceptionHandler.respondWithNotFoundException(USER_NOT_FOUND)
        }
    }

    private suspend fun checkIfUsersExistWithUserData(userId: String?): Pair<User?, Boolean> {
        val user = userId?.let { userCollection.findOneById(it) }
        return Pair(user, user != null)
    }
}