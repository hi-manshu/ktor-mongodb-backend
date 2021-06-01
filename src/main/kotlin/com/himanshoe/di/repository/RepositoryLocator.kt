package com.himanshoe.di.repository

import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.auth.repository.AuthRepositoryImpl
import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.di.ServiceLocator
import com.himanshoe.di.database.DatabaseLocator
import com.himanshoe.posts.repository.PostsRepository
import com.himanshoe.posts.repository.PostsRepositoryImpl
import com.himanshoe.user.repository.UserRepository
import com.himanshoe.user.repository.UserRepositoryImpl

object RepositoryLocator {

    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl(
            DatabaseLocator.provideDatabase().userCollection,
            JwtConfig.instance,
            ServiceLocator.provideExceptionHandler()
        )
    }

    fun providePostsRepository(): PostsRepository {
        return PostsRepositoryImpl(
            DatabaseLocator.provideDatabase().postCollection,
            DatabaseLocator.provideDatabase().userCollection,
            ServiceLocator.provideExceptionHandler()
        )
    }

    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(
            DatabaseLocator.provideDatabase().userCollection,
            ServiceLocator.provideExceptionHandler()
        )
    }

    fun provideRepositoryProvider(): RepositoryProvider {
        return RepositoryProviderImpl()
    }
}