package com.himanshoe.user

import io.ktor.locations.*

@Location(UserConstant.USER_INFO)
data class UserInfo(val userId: String)

@Location(UserConstant.CURRENT_USER)
class CurrentUser

@Location(UserConstant.UPDATE_USER)
class UpdateUser