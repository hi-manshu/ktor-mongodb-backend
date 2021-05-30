package com.himanshoe.di.domain

import com.himanshoe.auth.domain.CreateUserAuthTokenUseCase
import com.himanshoe.auth.domain.LoginUserUseCase
import com.himanshoe.user.domain.CurrentUserDetailUseCase
import com.himanshoe.user.domain.FindUserByIdUseCase

interface DomainProvider {

    fun provideCreateUserAuthTokenUseCase(): CreateUserAuthTokenUseCase

    fun provideLoginUserUseCase(): LoginUserUseCase

    fun provideFindUserByIdUseCase(): FindUserByIdUseCase

    fun provideCurrentUserDetailUseCase(): CurrentUserDetailUseCase
}