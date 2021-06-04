package com.himanshoe.di.repository

import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.di.service.ServiceProvider
import com.himanshoe.posts.repository.PostsRepository
import com.himanshoe.user.repository.UserRepository

class RepositoryProviderImpl(private val serviceProvider: ServiceProvider) : RepositoryProvider {

    override fun provideAuthRepository(): AuthRepository {
        return RepositoryLocator.provideAuthRepository(
            serviceProvider.provideUserApiService()
        )
    }

    override fun provideUserRepository(): UserRepository {
        return RepositoryLocator.provideUserRepository(serviceProvider.provideUserApiService())
    }

    override fun providePostsRepository(): PostsRepository {
        return RepositoryLocator.providePostsRepository(
            serviceProvider.providePostApiService(),
            serviceProvider.provideUserApiService()
        )
    }
}