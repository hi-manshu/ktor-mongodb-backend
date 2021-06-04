package com.himanshoe.user

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(UserConstant.USER_INFO)
data class UserInfo(val userId: String)

@KtorExperimentalLocationsAPI
@Location(UserConstant.CURRENT_USER)
class CurrentUser

@KtorExperimentalLocationsAPI
@Location(UserConstant.UPDATE_USER)
class UpdateUser

@KtorExperimentalLocationsAPI
@Location(UserConstant.USER_POSTS)
data class UserPosts(val userId: String)
