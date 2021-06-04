package com.himanshoe.di

import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.base.http.ExceptionHandlerImpl

object ConfigLocator {

    fun provideJwtConfig() {
        return JwtConfig.initialize("ktor-backend")
    }

    fun provideExceptionHandler(): ExceptionHandler {
        return ExceptionHandlerImpl()
    }
}
