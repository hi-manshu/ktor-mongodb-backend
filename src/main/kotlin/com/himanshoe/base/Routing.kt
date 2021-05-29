package com.himanshoe.base

import com.himanshoe.auth.authRoute
import com.himanshoe.di.ServiceLocator
import com.himanshoe.user.userRoutes
import io.ktor.routing.*
import io.ktor.locations.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*

fun Application.configureRoutingAndSerialization() {
    install(Locations)
        install(ContentNegotiation) {
            gson()
    }

    routing {
        userRoutes()
        authRoute(ServiceLocator.authRepository())
    }
}
