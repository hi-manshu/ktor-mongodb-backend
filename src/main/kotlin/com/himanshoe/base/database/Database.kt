package com.himanshoe.base.database

import com.himanshoe.posts.Post
import com.himanshoe.user.User
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

interface Database {

    val initializeName: String

    val mongoClient: CoroutineClient

    val database: CoroutineDatabase

    val userCollection: CoroutineCollection<User>

    val postCollection: CoroutineCollection<Post>
}
