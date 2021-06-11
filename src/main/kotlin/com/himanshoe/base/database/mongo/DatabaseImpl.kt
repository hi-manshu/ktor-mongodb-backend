package com.himanshoe.base.database.mongo

import com.himanshoe.feature.comment.Comment
import com.himanshoe.feature.posts.Post
import com.himanshoe.feature.user.User
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class DatabaseImpl(private val clientName: String) : Database {

    override val initializeName: String
        get() = clientName

    override val mongoClient: CoroutineClient
        get() = KMongo.createClient().coroutine

    override val database: CoroutineDatabase
        get() = mongoClient.getDatabase(initializeName)

    override val userCollection: CoroutineCollection<User>
        get() = database.getCollection()

    override val postCollection: CoroutineCollection<Post>
        get() = database.getCollection()

    override val commentCollection: CoroutineCollection<Comment>
        get() = database.getCollection()
}
