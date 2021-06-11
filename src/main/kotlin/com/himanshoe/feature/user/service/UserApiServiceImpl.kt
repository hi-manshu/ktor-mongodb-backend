package com.himanshoe.feature.user.service

import com.himanshoe.base.database.redis.RedisClient
import com.himanshoe.feature.user.User
import com.himanshoe.util.Logger
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.exclude
import org.litote.kmongo.fields
import org.litote.kmongo.util.KMongoUtil

class UserApiServiceImpl(
    private val userCollection: CoroutineCollection<User>,
    private val redisClient: RedisClient,
) : UserApiService {

    companion object {
        private const val IN_OPERATOR_START = "{_id : {${'$'}in: "
        private const val IN_OPERATOR_END = "}}"
    }

    override suspend fun populate(userIds: List<String>): List<User> {
        val fieldsToBeExcluded: Bson =
            fields(exclude(User::passwordHash, User::role, User::userPosts, User::updatedAt, User::age, User::gender))
        val filter = "$IN_OPERATOR_START$userIds$IN_OPERATOR_END"
        return userCollection
            .findAndCast<User>(
                KMongoUtil.toBson(filter)
            ).projection(fieldsToBeExcluded)
            .toList()
    }

    override suspend fun insertUser(user: User): Boolean {
        val newUser = userCollection.insertOne(user)
        val isSuccess = newUser.wasAcknowledged()
        return if (isSuccess) {
            Logger.d(user.username)
            redisClient.write(user.username, user)
            true
        } else {
            false
        }
    }

    override suspend fun findUserByUsername(username: String): User? {
        return userCollection.findOne(User::username eq username)
    }

    override suspend fun findUserByUserId(userId: String): User? {
        val fields: Bson = fields(exclude(User::passwordHash))
        return userId.let {
            userCollection.find(User::userId eq userId)
                .projection(fields)
                .first()
        }
    }

    override suspend fun updateUserById(userId: String, user: User): Boolean? {
        return userCollection.updateOneById(userId, user, updateOnlyNotNullProperties = true).wasAcknowledged()
    }
}
