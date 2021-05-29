package com.himanshoe.util

sealed class Response<out T> {
    data class Success<out R>   (
        val status: Boolean = true,
        val data: R
    ) : Response<R>()

    data class Failure(
        val status: Boolean = false,
        val message: String?,
        val throwable: Throwable?
    ) : Response<Nothing>()
}