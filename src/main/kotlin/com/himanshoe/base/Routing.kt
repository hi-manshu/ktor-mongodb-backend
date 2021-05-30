package com.himanshoe.base

import com.himanshoe.auth.authRoutes
import com.himanshoe.di.domain.DomainLocator
import com.himanshoe.user.userRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.locations.*
import io.ktor.routing.*


val domainLocator = DomainLocator

fun Application.configureRoutingAndSerialization() {
    install(Locations)
    install(Routing)
    install(ContentNegotiation) {
        gson()
    }

    routing {
        userRoutes(domainLocator.provideDomainProvider())
        authRoutes(domainLocator.provideDomainProvider())
    }
}

