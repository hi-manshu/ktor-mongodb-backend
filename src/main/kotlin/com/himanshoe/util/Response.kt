package com.himanshoe.util

import io.ktor.http.*

data class BaseResponse<out T : Any>(
    val statusCode: HttpStatusCode,
    val data: T? = null,
    val message: String? = null
)