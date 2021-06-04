package com.himanshoe.feature.auth.repository

import com.himanshoe.feature.auth.AuthRequest
import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.feature.user.User
import com.himanshoe.feature.user.service.UserApiService
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.SuccessResponse
import com.himanshoe.util.checkHashForPassword
import com.himanshoe.util.getHashWithSalt
import io.ktor.http.HttpStatusCode

/**
 * [AuthRepositoryImpl] is a implementation for [AuthRepository]
 * @param userCollection is the User Collection in KMongo
 * @param jwtConfig Config file responsible for JWT Tokens
 * @param exceptionHandler  is the container containing all the Exceptions
 */
class AuthRepositoryImpl(
    private val userApiService: UserApiService,
    private val jwtConfig: JwtConfig,
    private val exceptionHandler: ExceptionHandler
) : AuthRepository {

    /**
     * All static constant containing the Error code for [ExceptionHandler]
     */
    companion object {
        private const val USER_ALREADY_EXIST_MESSAGE = "User already exists, Please login"
        private const val EITHER_USERNAME_PASSWORD_INCORRECT = "Either username or password is incorrect"
        private const val NOT_AUTHORIZED = "Not authorised"
        private const val USER_DONT_EXIST_MESSAGE = "User doesn't exists, Please register"
        private const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again"
    }

    override suspend fun createToken(authRequest: AuthRequest): BaseResponse<Any> {
        return if (checkIfUsersExist(authRequest.username)) {
            throw exceptionHandler.respondWithAlreadyExistException(USER_ALREADY_EXIST_MESSAGE)
        } else {
            val hashPassword = getHashWithSalt(authRequest.password)
            val user = User(authRequest.username, hashPassword)
            val responseIsSuccessful = userApiService.insertUser(user)
            when {
                responseIsSuccessful -> SuccessResponse(
                    data = jwtConfig.makeAccessToken(user.userId),
                    statusCode = HttpStatusCode.Created
                )
                else -> throw exceptionHandler.respondWithGenericException(SOMETHING_WENT_WRONG)
            }
        }
    }

    override suspend fun loginUser(authRequest: AuthRequest): BaseResponse<Any> {
        return if (checkIfUsersExist(authRequest.username)) {
            val user: User? = userApiService.findUserByUsername(authRequest.username)
            if (user != null) {
                val hashedPasswordIsSame = user.passwordHash?.let { checkHashForPassword(authRequest.password, it) }
                when (hashedPasswordIsSame) {
                    true -> SuccessResponse(
                        data = jwtConfig.makeAccessToken(user.userId),
                        statusCode = HttpStatusCode.OK
                    )
                    else -> throw exceptionHandler.respondWithUnauthorizedException(EITHER_USERNAME_PASSWORD_INCORRECT)
                }
            } else throw exceptionHandler.respondWithUnauthorizedException(NOT_AUTHORIZED)
        } else {
            throw exceptionHandler.respondWithUnauthorizedException(USER_DONT_EXIST_MESSAGE)
        }
    }

    private suspend fun checkIfUsersExist(username: String): Boolean {
        return userApiService.findUserByUsername(username) != null
    }
}
