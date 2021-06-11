package com.himanshoe.base

import com.himanshoe.base.database.redis.RedisClient
import com.himanshoe.di.domain.DomainLocator
import com.himanshoe.feature.auth.authRoutes
import com.himanshoe.feature.comment.commentRoutes
import com.himanshoe.feature.posts.postsRoute
import com.himanshoe.feature.user.userRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.locations.*
import io.ktor.routing.*

val domainLocator = DomainLocator

fun Application.configureRoutingAndSerialization(redisClient: RedisClient) {
    install(Locations)
    install(Routing)
    install(ContentNegotiation) {
        gson ()
    }

    routing {
        userRoutes(domainLocator.provideDomainProvider())
        authRoutes(domainLocator.provideDomainProvider(),redisClient)
        postsRoute(domainLocator.provideDomainProvider())
        commentRoutes(domainLocator.provideDomainProvider())
    }
}
