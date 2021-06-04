package com.himanshoe.user.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.user.User
import com.himanshoe.user.service.UserApiService
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.SuccessResponse
import io.ktor.http.HttpStatusCode
import java.util.Date

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val exceptionHandler: ExceptionHandler
) : UserRepository {

    companion object {
        private const val USER_NOT_FOUND = "User not found"
        private const val CANNOT_UPDATE = "You cannot update the data"
    }

    override suspend fun findUserById(userId: String?): BaseResponse<Any> {
        val (user, doExist) = checkIfUsersExistWithUserData(userId)
        if (userId != null && doExist) {
            return SuccessResponse(HttpStatusCode.Found, user)
        } else {
            throw exceptionHandler.respondWithNotFoundException(USER_NOT_FOUND)
        }
    }

    override suspend fun currentUser(userId: String?): BaseResponse<Any> {
        val (user, doExist) = checkIfUsersExistWithUserData(userId)
        if (userId != null && doExist) {
            return SuccessResponse(HttpStatusCode.Found, user)
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
            val isUpdated = userApiService.updateUserById(userId, userUpdated)
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
        val user = userId?.let { userApiService.findUserByUserId(it) }
        return Pair(user, user != null)
    }
}
