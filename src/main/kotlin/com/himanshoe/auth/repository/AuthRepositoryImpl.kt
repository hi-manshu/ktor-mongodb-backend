package com.himanshoe.auth.repository

import com.himanshoe.auth.AuthRequest
import com.himanshoe.user.UserModel
import com.himanshoe.util.getHashWithSalt
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class AuthRepositoryImpl(private val userCollection: CoroutineCollection<UserModel>) : AuthRepository {

    override suspend fun createToken(authRequest: AuthRequest): String {
        return if (checkIfUsersExist(authRequest.username)) {
            "User already exists"
        } else {
            val hashPassword = getHashWithSalt(authRequest.password)
            userCollection.insertOne(UserModel(authRequest.password, hashPassword))
            hashPassword
        }
    }

    override suspend fun checkIfUsersExist(username: String): Boolean {
        return userCollection.findOne(UserModel::username eq username) != null
    }
}