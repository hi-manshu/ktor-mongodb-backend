package com.himanshoe.di.domain

import com.himanshoe.auth.domain.CreateUserAuthTokenUseCase
import com.himanshoe.auth.domain.LoginUserUseCase
import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.di.repository.RepositoryLocator
import com.himanshoe.user.domain.CurrentUserDetailUseCase
import com.himanshoe.user.domain.FindUserByIdUseCase
import com.himanshoe.user.domain.GetUserPostsUseCase
import com.himanshoe.user.domain.UpdateCurrentUserUseCase
import com.himanshoe.user.repository.UserRepository

/**
 * [DomainLocator] creates a collection of instances of all the domains
 */
object DomainLocator {
    /**
     * [provideCreateUserAuthTokenUseCase] provides the [CreateUserAuthTokenUseCase] instance to [DomainProvider]
     */
    fun provideCreateUserAuthTokenUseCase(authRepository: AuthRepository): CreateUserAuthTokenUseCase {
        return CreateUserAuthTokenUseCase(authRepository)
    }

    /**
     * [provideLoginUserUseCase] provides the [LoginUserUseCase] instance to [DomainProvider]
     */
    fun provideLoginUserUseCase(authRepository: AuthRepository): LoginUserUseCase {
        return LoginUserUseCase(authRepository)
    }

    /**
     * [provideFindUserByIdUseCase] provides the [FindUserByIdUseCase] instance to [DomainProvider]
     */
    fun provideFindUserByIdUseCase(userRepository: UserRepository): FindUserByIdUseCase {
        return FindUserByIdUseCase(userRepository)
    }

    /**
     * [provideCurrentUserDetailUseCase] provides the [CurrentUserDetailUseCase] instance to [DomainProvider]
     */
    fun provideCurrentUserDetailUseCase(userRepository: UserRepository): CurrentUserDetailUseCase {
        return CurrentUserDetailUseCase(userRepository)
    }

    /**
     * [provideUpdateCurrentUserUseCase] provides the [UpdateCurrentUserUseCase] instance to [DomainProvider]
     */
    fun provideUpdateCurrentUserUseCase(userRepository: UserRepository): UpdateCurrentUserUseCase {
        return UpdateCurrentUserUseCase(userRepository)
    }

    /**
     * [provideGetUserPostsUseCase] provides the [GetUserPostsUseCase] instance to [DomainProvider]
     */
    fun provideGetUserPostsUseCase(userRepository: UserRepository): GetUserPostsUseCase {
        return GetUserPostsUseCase(userRepository)
    }

    /**
     * [provideDomainProvider] provides the [DomainProvider] instance
     */
    fun provideDomainProvider(): DomainProvider {
        return DomainProviderImpl(RepositoryLocator.provideRepositoryProvider())
    }
}