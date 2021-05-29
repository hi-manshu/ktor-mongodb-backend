package com.himanshoe.di

import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.auth.repository.AuthRepositoryImpl
import com.himanshoe.base.Database
import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.base.auth.JwtConfigImpl
import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.base.http.ExceptionHandlerImpl

object ServiceLocator {


    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl(Database.userCollection, provideJwtConfig(), provideExceptionHandler())
    }

    fun provideJwtConfig(): JwtConfig {
        return JwtConfigImpl()
    }

    fun provideExceptionHandler(): ExceptionHandler {
        return ExceptionHandlerImpl()
    }

}