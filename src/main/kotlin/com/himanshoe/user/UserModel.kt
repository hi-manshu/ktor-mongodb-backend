package com.himanshoe.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class UserModel(
    val username: String = "",
    val passwordHash: String = "",
    @BsonId
    val id: String = ObjectId().toString()
)