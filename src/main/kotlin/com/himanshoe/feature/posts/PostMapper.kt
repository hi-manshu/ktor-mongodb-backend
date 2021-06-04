package com.himanshoe.feature.posts

import com.himanshoe.feature.user.User
import com.himanshoe.feature.user.service.UserApiService

suspend fun Post.toPostWithUser(userApiService: UserApiService, response: List<Post>): PostList {
    val invertedCommas = '"'

    val likes: List<String> = response.map { posts ->
        posts.likes.map { userId ->
            "$invertedCommas$userId$invertedCommas"
        }
    }.flatten()
    return this.remodelPostList(likes, userApiService)
}

suspend fun Post.toPostWithUserDetails(userApiService: UserApiService, likesList: List<String>): PostList {
    val invertedCommas = '"'

    val likes: List<String> = likesList.map { userId ->
        "$invertedCommas$userId$invertedCommas"
    }
    return this.remodelPostList(likes, userApiService)
}

private suspend fun Post.remodelPostList(userIds: List<String>, userApiService: UserApiService): PostList {
    val users: List<User> = userApiService.populate(userIds)
    return PostList(
        postId,
        title,
        post,
        users,
        createdAt,
        createdBy,
        updatedAt,
        shortUrl,
        isDeleted,
        comments,
        createdByUser
    )
}
