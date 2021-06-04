package com.himanshoe.util

import com.himanshoe.base.auth.UserIdPrincipalForUser
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.request.receive
import io.ktor.util.pipeline.PipelineContext

fun PipelineContext<*, ApplicationCall>.getUserId(): String? {
    val principal = this.call.authentication.principal<UserIdPrincipalForUser>()
    return principal?.userId
}

suspend inline fun <reified T : Any> PipelineContext<*, ApplicationCall>.getBodyContent(): T {
    return call.receive()
}
