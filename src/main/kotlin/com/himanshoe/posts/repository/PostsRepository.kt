package com.himanshoe.posts.repository

import com.himanshoe.util.BaseResponse

interface PostsRepository {

    suspend fun fetchPosts(page: Int,count:Int): BaseResponse<Any>

}