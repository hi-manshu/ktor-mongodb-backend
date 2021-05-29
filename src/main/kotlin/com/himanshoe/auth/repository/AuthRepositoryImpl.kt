package com.himanshoe.auth.repository

import com.himanshoe.auth.AuthRequest
import com.himanshoe.base.generateToken
import com.himanshoe.user.UserModel
import com.himanshoe.util.Response
import com.himanshoe.util.getHashWithSalt
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class AuthRepositoryImpl(private val userCollection: CoroutineCollection<UserModel>) : AuthRepository {

    override suspend fun createToken(authRequest: AuthRequest): Response<Any> {
        return if (checkIfUsersExist(authRequest.username)) {
            val message = "User already exists, Please login"
            Response.Failure(message = message, throwable = null)
        } else {
            val hashPassword = getHashWithSalt(authRequest.password)
            val user = UserModel(authRequest.username, hashPassword)
            userCollection.insertOne(user)
            Response.Success(value = generateToken(user.id.toString()))
        }
    }

    override suspend fun loginUser(authRequest: AuthRequest): Response<Any> {
       return if (checkIfUsersExist(authRequest.username)) {
            val hashPassword = getHashWithSalt(authRequest.password)
            val user: UserModel? = userCollection.findOne(UserModel::passwordHash eq hashPassword)
            Response.Success(value = generateToken(user?.id.toString()))
        } else {
            val message = "User doesn't exists, Please register"
            Response.Failure(message = message, throwable = null)
        }
    }

    override suspend fun checkIfUsersExist(username: String): Boolean {
        return userCollection.findOne(UserModel::username eq username) != null
    }
}