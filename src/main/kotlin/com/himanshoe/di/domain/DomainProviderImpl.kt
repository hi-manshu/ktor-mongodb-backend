package com.himanshoe.di.domain

import com.himanshoe.auth.domain.CreateUserAuthTokenUseCase
import com.himanshoe.auth.domain.LoginUserUseCase
import com.himanshoe.di.repository.RepositoryProvider
import com.himanshoe.user.domain.CurrentUserDetailUseCase
import com.himanshoe.user.domain.FindUserByIdUseCase
import com.himanshoe.user.domain.UpdateCurrentUserUseCase

class DomainProviderImpl(private val repositoryProvider: RepositoryProvider) : DomainProvider {

    override fun provideCreateUserAuthTokenUseCase(): CreateUserAuthTokenUseCase {
        return DomainLocator.provideCreateUserAuthTokenUseCase(repositoryProvider.provideAuthRepository())
    }

    override fun provideLoginUserUseCase(): LoginUserUseCase {
        return DomainLocator.provideLoginUserUseCase(repositoryProvider.provideAuthRepository())
    }

    override fun provideFindUserByIdUseCase(): FindUserByIdUseCase {
        return DomainLocator.provideFindUserByIdUseCase(repositoryProvider.provideUserRepository())
    }

    override fun provideCurrentUserDetailUseCase(): CurrentUserDetailUseCase {
        return DomainLocator.provideCurrentUserDetailUseCase(repositoryProvider.provideUserRepository())
    }

    override fun provideUpdateCurrentUserUseCase(): UpdateCurrentUserUseCase {
        return DomainLocator.provideUpdateCurrentUserUseCase(repositoryProvider.provideUserRepository())
    }
}