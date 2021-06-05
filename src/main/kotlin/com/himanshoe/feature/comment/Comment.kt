package com.himanshoe.feature.comment

import com.himanshoe.feature.user.User
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Comment(
    @BsonId
    val postId: String = ObjectId().toString(),
    val createdBy: String? = null,
    val comment: String,
    val createdAt: String? = null,
    val createdByUser: User? = null
)
