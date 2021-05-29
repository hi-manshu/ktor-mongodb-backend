package com.himanshoe.auth

import com.himanshoe.auth.repository.AuthRepository
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.authRoute(authRepository: AuthRepository) {

    routing {
        post(AuthConstant.LOGIN) {
            val authRequest = call.receive<AuthRequest>()
            val token = authRepository.createToken(authRequest)
            call.respond(token)
        }
    }
}