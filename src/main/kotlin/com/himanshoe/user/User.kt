package com.himanshoe.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val username: String = "",
    val passwordHash: String? = null,
    @BsonId
    val userId: String = ObjectId().toString(),
    val role: String = Role.Creator.role,
    val gender: String? = null,
    val age: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val createdAt: String? = null,
    val userPosts :List<String> = emptyList(),
    val updatedAt: String? = null
) {

    fun asResponse(): User {
        return User(username, null, userId, role, null, null, null, null)
    }
}

enum class Role(val role: String) {
    Creator("creator"),
    Admin("admin");
}

enum class Gender(val gender: String) {
    Male("male"),
    Female("female"),
    Either("either"),
}