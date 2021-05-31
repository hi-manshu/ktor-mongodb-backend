package com.himanshoe.posts

import com.himanshoe.di.domain.DomainProvider
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.postsRoute(domainProvider: DomainProvider) {
    routing {

        get<PostsList> { request ->
            val response = domainProvider.provideGetPostsUseCase().invoke(Pair(request.page,request.page))
            call.respond(response)
        }
    }
}