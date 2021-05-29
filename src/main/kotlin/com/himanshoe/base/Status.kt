package com.himanshoe.base

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<AuthenticationException> { cause ->
            call.respond(HttpStatusCode.Unauthorized,cause.message.toString())
        }
        exception<AuthorizationException> { cause ->
            call.respond(HttpStatusCode.Forbidden,cause.message.toString())
        }
        exception<BadRequestException> { cause ->
            call.respond(HttpStatusCode.BadRequest,cause.message.toString())
        }
        exception<NotFoundException> { cause ->
            call.respond(HttpStatusCode.NotFound,cause.message.toString())
        }
    }
}
class AuthenticationException(message: String?) : RuntimeException(message)
class AuthorizationException(message: String?) : RuntimeException(message)
class BadRequestException(message: String?) : RuntimeException(message)
class NotFoundException(message: String?) : RuntimeException(message)       
