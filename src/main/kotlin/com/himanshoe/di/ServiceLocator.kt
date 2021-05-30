package com.himanshoe.di

import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.base.auth.JwtConfigImpl
import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.base.http.ExceptionHandlerImpl

object ServiceLocator {

    fun provideJwtConfig(): JwtConfig {
        return JwtConfigImpl()
    }

    fun provideExceptionHandler(): ExceptionHandler {
        return ExceptionHandlerImpl()
    }
}