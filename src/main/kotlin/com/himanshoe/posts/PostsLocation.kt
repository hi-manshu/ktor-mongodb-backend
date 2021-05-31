package com.himanshoe.posts

import io.ktor.locations.*


@Location(PostsConstant.POSTS)
data class PostsList(val page: Int, val count: Int)

@Location(PostsConstant.CREATE_POST)
class CreatePost