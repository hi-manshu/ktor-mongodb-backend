package com.himanshoe.user

import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.userRoutes() {
    routing {
        get<UserInfo> {
//            call.respond(UserModel("dsfsdf"))
        }
    }
}