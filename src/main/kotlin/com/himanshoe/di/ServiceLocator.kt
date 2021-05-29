package com.himanshoe.di

import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.auth.repository.AuthRepositoryImpl
import com.himanshoe.base.Database
import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.base.auth.JwtConfigImpl

object ServiceLocator {

    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl(Database.userCollection, provideJwtConfig())
    }

    fun provideJwtConfig(): JwtConfig {
        return JwtConfigImpl()
    }

}