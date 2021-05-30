package com.himanshoe.di.domain

import com.himanshoe.auth.domain.CreateUserAuthTokenUseCase
import com.himanshoe.auth.domain.LoginUserUseCase
import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.di.repository.RepositoryLocator
import com.himanshoe.user.domain.CurrentUserDetailUseCase
import com.himanshoe.user.domain.FindUserByIdUseCase
import com.himanshoe.user.repository.UserRepository

object DomainLocator {

    fun provideCreateUserAuthTokenUseCase(authRepository: AuthRepository): CreateUserAuthTokenUseCase {
        return CreateUserAuthTokenUseCase(authRepository)
    }

    fun provideLoginUserUseCase(authRepository: AuthRepository): LoginUserUseCase {
        return LoginUserUseCase(authRepository)
    }

    fun provideFindUserByIdUseCase(userRepository: UserRepository): FindUserByIdUseCase {
        return FindUserByIdUseCase(userRepository)
    }

    fun provideCurrentUserDetailUseCase(userRepository: UserRepository): CurrentUserDetailUseCase {
        return CurrentUserDetailUseCase(userRepository)
    }

    fun provideDomainProvider(): DomainProvider {
        return DomainProviderImpl(RepositoryLocator.provideRepositoryProvider())
    }
}