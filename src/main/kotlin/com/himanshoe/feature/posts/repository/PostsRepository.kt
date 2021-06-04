package com.himanshoe.feature.posts.repository

import com.himanshoe.feature.posts.Post
import com.himanshoe.util.BaseResponse

interface PostsRepository {

    suspend fun fetchPosts(page: Int, count: Int): BaseResponse<Any>

    suspend fun createPost(userId: String?, post: Post): BaseResponse<Any>

    suspend fun findPostById(postId: String?): BaseResponse<Any>

    suspend fun likeDislikePost(userId: String?, postId: String?, isLiked: Boolean): BaseResponse<Any>

    suspend fun deletePost(userId: String?, postId: String?): BaseResponse<Any>
}
