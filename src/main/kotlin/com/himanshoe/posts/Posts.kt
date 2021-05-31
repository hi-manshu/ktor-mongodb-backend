package com.himanshoe.posts

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Posts(
    @BsonId
    val userId: String = ObjectId().toString(),
    val title: String,
    val post: String,
    val likes: Int = 0,
    val comments: List<String> = emptyList()
)