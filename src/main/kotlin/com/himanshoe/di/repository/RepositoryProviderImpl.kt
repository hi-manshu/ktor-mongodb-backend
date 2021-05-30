package com.himanshoe.di.repository

import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.user.repository.UserRepository

class RepositoryProviderImpl : RepositoryProvider {

    override fun provideAuthRepository(): AuthRepository {
        return RepositoryLocator.provideAuthRepository()
    }

    override fun provideUserRepository(): UserRepository {
        return RepositoryLocator.provideUserRepository()
    }
}