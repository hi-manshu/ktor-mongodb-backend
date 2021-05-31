package com.himanshoe.posts

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Post(
    @BsonId
    val postId: String = ObjectId().toString(),
    val title: String,
    val post: String,
    val likes: List<String> = emptyList(),
    val createdAt: String? = null,
    val createdBy: String? = null,
    val updatedAt: String? = null,
    val shortUrl: String? = null,
    val isDeleted: Boolean? = false,
    val comments: List<String> = emptyList()
) {
    fun asResponse(): Post {
        return Post(postId, title, post, likes, createdAt, createdBy, updatedAt, shortUrl, null, comments)
    }
}