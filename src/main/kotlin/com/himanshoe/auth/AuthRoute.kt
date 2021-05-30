package com.himanshoe.auth

import com.himanshoe.di.domain.DomainProvider
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.authRoutes(domainProvider: DomainProvider) {

    routing {

        post(AuthConstant.LOGIN) {
            val authRequest = call.receive<AuthRequest>()
            val response = domainProvider.provideLoginUserUseCase().invoke(authRequest)
            call.respond(response)
        }

        post(AuthConstant.REGISTER) {
            val authRequest = call.receive<AuthRequest>()
            val response = domainProvider.provideCreateUserAuthTokenUseCase().invoke(authRequest)
            call.respond(response)
        }
    }
}