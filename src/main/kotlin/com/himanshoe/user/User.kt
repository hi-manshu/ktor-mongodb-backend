package com.himanshoe.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val username: String = "",
    val passwordHash: String?,
    @BsonId
    val userId: String = ObjectId().toString()
) {
    fun asResponse(): User {
        return User(username, null, userId)
    }
}