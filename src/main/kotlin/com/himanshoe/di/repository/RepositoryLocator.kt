package com.himanshoe.di.repository

import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.auth.repository.AuthRepositoryImpl
import com.himanshoe.base.Database
import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.di.ServiceLocator
import com.himanshoe.user.repository.UserRepository
import com.himanshoe.user.repository.UserRepositoryImpl

object RepositoryLocator {

    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl(
            Database.userCollection,
            JwtConfig.instance,
            ServiceLocator.provideExceptionHandler()
        )
    }

    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(
            Database.userCollection,
            ServiceLocator.provideExceptionHandler()
        )
    }

    fun provideRepositoryProvider(): RepositoryProvider {
        return RepositoryProviderImpl()
    }
}