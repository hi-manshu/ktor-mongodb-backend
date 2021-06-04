package com.himanshoe.posts

import com.himanshoe.user.User
import com.himanshoe.user.UserForCommentsAndLike
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.util.KMongoUtil

suspend fun Post.toPostWithUser(userCollection: CoroutineCollection<User>, response: List<Post>): PostList {
    val invertedCommas = '"'
    val likes: List<String> = response.map { posts ->
        posts.likes.map { userId ->
            "$invertedCommas$userId$invertedCommas"
        }
    }.flatten()

    val filter = """{_id : {${'$'}in: $likes}}"""
    val postListUsers: List<UserForCommentsAndLike> = userCollection.findAndCast<User>(
        KMongoUtil.toBson(filter)
    ).toList()
        .map {
            it.toUserForCommentsAndLike()
        }
    return PostList(
        postId,
        title,
        post,
        postListUsers,
        createdAt,
        createdBy,
        updatedAt,
        shortUrl,
        isDeleted,
        comments
    )

}