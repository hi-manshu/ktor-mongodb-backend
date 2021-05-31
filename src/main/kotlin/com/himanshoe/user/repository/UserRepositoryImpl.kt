package com.himanshoe.user.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.user.User
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.SuccessResponse
import io.ktor.http.*
import org.litote.kmongo.coroutine.CoroutineCollection
import java.util.*

class UserRepositoryImpl(
    private val userCollection: CoroutineCollection<User>,
    private val exceptionHandler: ExceptionHandler
) : UserRepository {

    companion object {
        private const val USER_NOT_FOUND = "User not found"
        private const val TOKEN_NOT_FOUND = "Token cannot be null or empty"
        private const val CANNOT_UPDATE = "You cannot update the data"
    }

    override suspend fun findUserById(userId: String?): BaseResponse<Any> {
        val (user, doExist) = checkIfUsersExistWithUserData(userId)
        if (userId != null && doExist) {
            return SuccessResponse(HttpStatusCode.Found, user?.asResponse())
        } else {
            throw exceptionHandler.respondWithNotFoundException(USER_NOT_FOUND)
        }
    }

    override suspend fun currentUser(userId: String?): BaseResponse<Any> {
        val (user, doExist) = checkIfUsersExistWithUserData(userId)
        if (userId != null && doExist) {
            return SuccessResponse(HttpStatusCode.Found, user?.asResponse())
        } else {
            throw exceptionHandler.respondWithNotFoundException(USER_NOT_FOUND)
        }
    }

    override suspend fun updateUser(userId: String, user: User): BaseResponse<Any> {
        val (userToBeUpdated, doExist) = checkIfUsersExistWithUserData(userId)
        if (userToBeUpdated != null && doExist && userToBeUpdated.userId == userId) {
            val userUpdated = user.copy(
                userId = userId,
                username = userToBeUpdated.username,
                passwordHash = userToBeUpdated.passwordHash,
                updatedAt = Date().toInstant().toString()
            )
            val isUpdated =
                userCollection.updateOneById(userId, userUpdated, updateOnlyNotNullProperties = true).wasAcknowledged()
            return SuccessResponse(HttpStatusCode.OK, isUpdated)
        } else {
            throw exceptionHandler.respondWithGenericException(CANNOT_UPDATE)
        }

    }

    override suspend fun fetchUserPosts(userId: String): BaseResponse<Any> {
        val (user, userExits) = checkIfUsersExistWithUserData(userId)
        if (userExits) {
            return SuccessResponse(HttpStatusCode.OK, user?.userPosts ?: emptyList<String>())
        } else {
            throw exceptionHandler.respondWithNotFoundException(USER_NOT_FOUND)
        }
    }

    private suspend fun checkIfUsersExistWithUserData(userId: String?): Pair<User?, Boolean> {
        val user = userId?.let { userCollection.findOneById(it) }
        return Pair(user, user != null)
    }
}