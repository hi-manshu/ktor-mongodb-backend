package com.himanshoe.user

import io.ktor.locations.*

@Location("/user/{userId}")
data class UserInfo(val userId:String)