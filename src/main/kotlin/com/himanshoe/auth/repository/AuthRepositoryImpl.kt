package com.himanshoe.auth.repository

import com.himanshoe.auth.AuthRequest
import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.user.UserModel
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.checkHashForPassword
import com.himanshoe.util.getHashWithSalt
import io.ktor.http.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class AuthRepositoryImpl(
    private val userCollection: CoroutineCollection<UserModel>,
    private val jwtConfig: JwtConfig,
    private val exceptionHandler: ExceptionHandler
) : AuthRepository {

    companion object {
        private const val USER_ALREADY_EXIST_MESSAGE = "User already exists, Please login"
        private const val EITHER_USERNAME_PASSWORD_INCORRECT = "Either username or password is incorrect"
        private const val NOT_AUTHORIZED = "Not authorised"
        private const val USER_DONT_EXIST_MESSAGE = "User doesn't exists, Please register"

    }

    override suspend fun createToken(authRequest: AuthRequest): BaseResponse<Any> {
        return if (checkIfUsersExist(authRequest.username)) {
            throw exceptionHandler.respondWithAlreadyExistException(USER_ALREADY_EXIST_MESSAGE)
        } else {
            val hashPassword = getHashWithSalt(authRequest.password)
            val user = UserModel(authRequest.username, hashPassword)
            userCollection.insertOne(user)
            BaseResponse(data = jwtConfig.makeAccessToken(user.userId), statusCode = HttpStatusCode.Created)
        }
    }

    override suspend fun loginUser(authRequest: AuthRequest): BaseResponse<Any> {
        return if (checkIfUsersExist(authRequest.username)) {
            val user: UserModel? = userCollection.findOne(UserModel::username eq authRequest.username)
            if (user != null) {
                val hashPassword = checkHashForPassword(authRequest.password, user.passwordHash)
                if (hashPassword) {
                    BaseResponse(data = jwtConfig.makeAccessToken(user.userId), statusCode = HttpStatusCode.OK)
                } else {
                    throw exceptionHandler.respondWithUnauthorizedException(EITHER_USERNAME_PASSWORD_INCORRECT)
                }
            } else {
                throw exceptionHandler.respondWithUnauthorizedException(NOT_AUTHORIZED)
            }
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(USER_DONT_EXIST_MESSAGE)
        }
    }

    override suspend fun checkIfUsersExist(username: String): Boolean {
        return userCollection.findOne(UserModel::username eq username) != null
    }
}