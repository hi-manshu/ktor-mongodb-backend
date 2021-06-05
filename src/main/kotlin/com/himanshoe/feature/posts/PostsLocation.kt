package com.himanshoe.feature.posts

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(PostsConstant.POSTS)
data class PostsList(val page: Int, val count: Int)

@KtorExperimentalLocationsAPI
@Location(PostsConstant.CREATE_POST)
class CreatePost

@KtorExperimentalLocationsAPI
@Location(PostsConstant.LIKE_DISLIKE_POST)
class LikeDislikePost

@KtorExperimentalLocationsAPI
@Location(PostsConstant.POST)
data class IndividualPost(val postId: String)
