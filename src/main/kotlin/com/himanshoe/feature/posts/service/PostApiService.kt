package com.himanshoe.feature.posts.service

import com.himanshoe.feature.posts.Post

interface PostApiService {

    suspend fun fetchPosts(page: Int, count: Int): Pair<List<Post>, Int>

    suspend fun createPost(userId: String?, post: Post): Boolean

    suspend fun findPostById(postId: String?): Post?

    suspend fun likeDislikePost(postId: String?, likes: List<String>): Boolean?

    suspend fun deletePost(postId: String?): Boolean?
}
