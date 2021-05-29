package com.himanshoe.auth

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.authRoute() {
    routing {
        post(AuthConstant.LOGIN) {
            val authRequest = call.receive<AuthRequest>()
            call.respond(authRequest)
        }
    }
}