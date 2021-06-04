package com.himanshoe.user

import com.himanshoe.di.domain.DomainProvider
import com.himanshoe.util.getBodyContent
import com.himanshoe.util.getUserId
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.locations.put
import io.ktor.response.*
import io.ktor.routing.*

fun Application.userRoutes(domainProvider: DomainProvider) {
    routing {

        get<UserInfo> { userRequest ->
            val response = domainProvider.provideFindUserByIdUseCase().invoke(userRequest.userId)
            call.respond(response)
        }

        authenticate {

            get<CurrentUser> {
                val userId = getUserId()
                val response = domainProvider.provideCurrentUserDetailUseCase().invoke(userId)
                call.respond(response)
            }

            put<UpdateUser> {
                val userId = getUserId()
                val user = getBodyContent<User>()
                val response =
                    domainProvider.provideUpdateCurrentUserUseCase().invoke(Pair(userId, user) as Pair<String, User>)
                call.respond(response)
            }

            get<UserPosts> { request ->
                val response = domainProvider.provideGetUserPostsUseCase().invoke(request.userId)
                call.respond(response)
            }
        }
    }
}
