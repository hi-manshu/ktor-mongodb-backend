package com.himanshoe.util

import com.himanshoe.base.auth.UserIdPrincipalForUser
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.getUserId(): String? {
    val principal = this.call.authentication.principal<UserIdPrincipalForUser>()
    return principal?.userId
}

suspend inline fun <reified T : Any> PipelineContext<*, ApplicationCall>.getBodyContent(): T {
    return call.receive()
}


