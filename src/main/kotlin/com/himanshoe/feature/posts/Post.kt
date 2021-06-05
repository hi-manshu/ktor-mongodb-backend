package com.himanshoe.feature.posts

import com.himanshoe.feature.comment.Comment
import com.himanshoe.feature.user.User
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Post(
    @BsonId
    val postId: String = ObjectId().toString(),
    val title: String? = null,
    val post: String? = null,
    val likes: List<String> = emptyList(),
    val createdAt: String? = null,
    val createdBy: String? = null,
    val updatedAt: String? = null,
    val shortUrl: String? = null,
    val isDeleted: Boolean? = null,
    val comments: List<String> = emptyList(),
    val createdByUser: User? = null
) {
    fun asResponse(): Post {
        return Post(postId, title, post, likes, createdAt, createdBy, updatedAt, shortUrl, null, comments)
    }
}

data class PostList(
    val postId: String? = null,
    val title: String? = null,
    val post: String? = null,
    val likes: List<User> = emptyList(),
    val createdAt: String? = null,
    val createdBy: String? = null,
    val updatedAt: String? = null,
    val shortUrl: String? = null,
    val isDeleted: Boolean? = null,
    val comments: List<Comment> = emptyList(),
    val createdByUser: User? = null
)
