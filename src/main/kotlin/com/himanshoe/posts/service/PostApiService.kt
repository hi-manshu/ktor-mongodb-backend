package com.himanshoe.posts.service

import com.himanshoe.posts.Post

interface PostApiService {

    suspend fun fetchPosts(page: Int, count: Int): Pair<List<Post>, Int>

    suspend fun createPost(userId: String?, post: Post): Boolean

    suspend fun findPostById(postId: String?): Post?

    suspend fun likeDislikePost(postId: String?, likes: List<String>): Boolean?

    suspend fun deletePost(postId: String?): Boolean?
}
