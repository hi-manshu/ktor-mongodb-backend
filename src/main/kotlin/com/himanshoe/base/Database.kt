package com.himanshoe.base

import com.himanshoe.user.UserModel
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object Database {

    private const val DATABASE_NAME = "ktor-mongo"

    private val mongoClient =  KMongo.createClient().coroutine

    private val database = mongoClient.getDatabase(DATABASE_NAME)

    val userCollection = database.getCollection<UserModel>()
}