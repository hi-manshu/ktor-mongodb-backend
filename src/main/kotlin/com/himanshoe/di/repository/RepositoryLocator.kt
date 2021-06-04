package com.himanshoe.di.repository

import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.auth.repository.AuthRepositoryImpl
import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.di.ConfigLocator
import com.himanshoe.di.service.ServiceLocator
import com.himanshoe.posts.repository.PostsRepository
import com.himanshoe.posts.repository.PostsRepositoryImpl
import com.himanshoe.posts.service.PostApiService
import com.himanshoe.user.repository.UserRepository
import com.himanshoe.user.repository.UserRepositoryImpl
import com.himanshoe.user.service.UserApiService

object RepositoryLocator {

    fun provideAuthRepository(provideUserApiService: UserApiService): AuthRepository {
        return AuthRepositoryImpl(
            provideUserApiService,
            JwtConfig.instance,
            ConfigLocator.provideExceptionHandler()
        )
    }

    fun providePostsRepository(postApiService: PostApiService, userApiService: UserApiService): PostsRepository {
        return PostsRepositoryImpl(
            postApiService,
            userApiService,
            ConfigLocator.provideExceptionHandler()
        )
    }

    fun provideUserRepository(userApiService: UserApiService): UserRepository {
        return UserRepositoryImpl(
            userApiService,
            ConfigLocator.provideExceptionHandler()
        )
    }

    fun provideRepositoryProvider(): RepositoryProvider {
        return RepositoryProviderImpl(ServiceLocator.provideServiceProvider())
    }
}