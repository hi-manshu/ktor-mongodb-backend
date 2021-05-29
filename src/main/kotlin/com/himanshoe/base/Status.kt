package com.himanshoe.base

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<AuthenticationException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.Unauthorized, cause.message.toString()))
        }
        exception<AuthorizationException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.Forbidden, cause.message.toString()))
        }
        exception<BadRequestException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.BadRequest, cause.message.toString()))
        }
        exception<NotFoundException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.NotFound, cause.message.toString()))
        }
        exception<ConflictException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.Conflict, cause.message.toString()))
        }
        exception<SomethingWentWrongException> { cause ->
            call.respond(ExceptionResponse(HttpStatusCode.ExpectationFailed, cause.message.toString()))
        }
    }
}

class AuthenticationException(message: String?) : RuntimeException(message)

class ConflictException(message: String?) : RuntimeException(message)

class AuthorizationException(message: String?) : RuntimeException(message)

class BadRequestException(message: String?) : RuntimeException(message)

class NotFoundException(message: String?) : RuntimeException(message)

class SomethingWentWrongException(message: String?) : RuntimeException(message)

data class ExceptionResponse(val code: HttpStatusCode, val message: String? = null)
