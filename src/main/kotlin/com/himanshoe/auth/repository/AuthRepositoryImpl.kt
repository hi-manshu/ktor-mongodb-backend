package com.himanshoe.auth.repository

import com.himanshoe.auth.AuthRequest
import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.user.UserModel
import com.himanshoe.util.Response
import com.himanshoe.util.checkHashForPassword
import com.himanshoe.util.getHashWithSalt
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class AuthRepositoryImpl(
    private val userCollection: CoroutineCollection<UserModel>,
    private val jwtConfig: JwtConfig
) : AuthRepository {

    override suspend fun createToken(authRequest: AuthRequest): Response<Any> {
        return if (checkIfUsersExist(authRequest.username)) {
            val message = "User already exists, Please login"
            Response.Failure(message = message, throwable = null)
        } else {
            val hashPassword = getHashWithSalt(authRequest.password)
            val user = UserModel(authRequest.username, hashPassword)
            userCollection.insertOne(user)
            Response.Success(data = jwtConfig.makeAccessToken(user.userId))

        }
    }

    override suspend fun loginUser(authRequest: AuthRequest): Response<Any> {
        return if (checkIfUsersExist(authRequest.username)) {
            val user: UserModel? = userCollection.findOne(UserModel::username eq authRequest.username)
            if (user != null) {
                val hashPassword = checkHashForPassword(authRequest.password, user.passwordHash)
                if (hashPassword) {
                    Response.Success(data = jwtConfig.makeAccessToken(user.userId))
                } else {
                    Response.Failure(message = "message", throwable = null)
                }
            } else {
                Response.Failure(message = "message", throwable = null)
            }
        } else {
            val message = "User doesn't exists, Please register"
            Response.Failure(message = message, throwable = null)
        }
    }

    override suspend fun checkIfUsersExist(username: String): Boolean {
        return userCollection.findOne(UserModel::username eq username) != null
    }
}