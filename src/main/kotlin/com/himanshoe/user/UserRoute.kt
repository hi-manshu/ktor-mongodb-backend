package com.himanshoe.user

import com.himanshoe.di.domain.DomainProvider
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.userRoutes(domainProvider: DomainProvider) {
    routing {
        get<UserInfo> { userRequest ->
            val response = domainProvider.provideFindUserByIdUseCase().invoke(userRequest.userId)
            call.respond(response)
        }
    }
}