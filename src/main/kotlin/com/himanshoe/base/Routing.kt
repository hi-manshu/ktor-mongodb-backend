package com.himanshoe.base

import com.himanshoe.auth.authRoutes
import com.himanshoe.di.domain.DomainLocator
import com.himanshoe.posts.postsRoute
import com.himanshoe.user.userRoutes
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.locations.Locations
import io.ktor.routing.Routing
import io.ktor.routing.routing
import io.ktor.features.*
import io.ktor.gson.*


val domainLocator = DomainLocator

fun Application.configureRoutingAndSerialization() {
    install(Locations)
    install(Routing)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        userRoutes(domainLocator.provideDomainProvider())
        authRoutes(domainLocator.provideDomainProvider())
        postsRoute(domainLocator.provideDomainProvider())
    }
}
