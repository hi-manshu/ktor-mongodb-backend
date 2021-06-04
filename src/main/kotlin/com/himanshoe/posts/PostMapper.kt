package com.himanshoe.posts

import com.himanshoe.user.User
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.exclude
import org.litote.kmongo.fields
import org.litote.kmongo.util.KMongoUtil

suspend fun Post.toPostWithUser(userCollection: CoroutineCollection<User>, response: List<Post>): PostList {
    val invertedCommas = '"'

    val likes: List<String> = response.map { posts ->
        posts.likes.map { userId ->
            "$invertedCommas$userId$invertedCommas"
        }
    }.flatten()
    val fieldsToBeExcluded: Bson = fields(exclude(User::passwordHash, User::role, User::userPosts, User::updatedAt, User::age, User::gender))
    val filter = """{_id : {${'$'}in: $likes}}"""
    val postListUsers: List<User> = userCollection
        .findAndCast<User>(
            KMongoUtil.toBson(filter)
        ).projection(fieldsToBeExcluded)
        .toList()

    return PostList(postId, title, post, postListUsers, createdAt, createdBy, updatedAt, shortUrl, isDeleted, comments,createdByUser)

}

