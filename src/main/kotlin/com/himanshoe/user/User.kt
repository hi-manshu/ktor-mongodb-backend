package com.himanshoe.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val username: String = "",
    val passwordHash: String?,
    @BsonId
    val userId: String = ObjectId().toString(),
    val role: String = Role.Creator.role
) {

    fun asResponse(): User {
        return User(username, null, userId)
    }

}

enum class Role(val role: String) {
    Creator("creator"),
    Admin("admin");
}