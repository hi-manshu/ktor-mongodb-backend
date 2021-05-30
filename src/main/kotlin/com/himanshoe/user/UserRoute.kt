package com.himanshoe.user

import com.himanshoe.base.auth.UserPrincipal
import com.himanshoe.di.domain.DomainProvider
import com.himanshoe.util.CommonConstant
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.userRoutes(domainProvider: DomainProvider) {
    routing {
        get<UserInfo> { userRequest ->
            val response = domainProvider.provideFindUserByIdUseCase().invoke(userRequest.userId)
            call.respond(response)
        }
        authenticate {
            get<CurrentUser> { userRequest ->
                val principal = call.principal<UserPrincipal>()
                val token = call.request.header(CommonConstant.Authorization)
                call.respond(token.toString())
            }
        }
    }
}