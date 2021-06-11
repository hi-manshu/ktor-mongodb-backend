package com.himanshoe.base.database.mongo

import com.himanshoe.feature.comment.Comment
import com.himanshoe.feature.posts.Post
import com.himanshoe.feature.user.User
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

interface Database {

    val initializeName: String

    val mongoClient: CoroutineClient

    val database: CoroutineDatabase

    val userCollection: CoroutineCollection<User>

    val postCollection: CoroutineCollection<Post>

    val commentCollection: CoroutineCollection<Comment>
}
