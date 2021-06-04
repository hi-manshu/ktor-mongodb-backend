package com.himanshoe.auth

import com.himanshoe.di.domain.DomainProvider
import com.himanshoe.util.getBodyContent
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.authRoutes(domainProvider: DomainProvider) {

    routing {

        post<LoginUser> {
            val authRequest = getBodyContent<AuthRequest>()
            val response = domainProvider.provideLoginUserUseCase().invoke(authRequest)
            call.respond(response)
        }

        post<RegisterUser> {
            val authRequest = getBodyContent<AuthRequest>()
            val response = domainProvider.provideCreateUserAuthTokenUseCase().invoke(authRequest)
            call.respond(response)
        }
    }
}
