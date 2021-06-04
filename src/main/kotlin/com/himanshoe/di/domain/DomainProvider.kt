package com.himanshoe.di.domain

import com.himanshoe.feature.auth.domain.CreateUserAuthTokenUseCase
import com.himanshoe.feature.auth.domain.LoginUserUseCase
import com.himanshoe.feature.posts.domain.*
import com.himanshoe.feature.user.domain.CurrentUserDetailUseCase
import com.himanshoe.feature.user.domain.FindUserByIdUseCase
import com.himanshoe.feature.user.domain.GetUserPostsUseCase
import com.himanshoe.feature.user.domain.UpdateCurrentUserUseCase

interface DomainProvider {

    fun provideCreateUserAuthTokenUseCase(): CreateUserAuthTokenUseCase

    fun provideLoginUserUseCase(): LoginUserUseCase

    fun provideFindUserByIdUseCase(): FindUserByIdUseCase

    fun provideCurrentUserDetailUseCase(): CurrentUserDetailUseCase

    fun provideUpdateCurrentUserUseCase(): UpdateCurrentUserUseCase

    fun provideGetUserPostsUseCase(): GetUserPostsUseCase

    fun provideGetPostsUseCase(): GetPostsUseCase

    fun provideCreatePostUseCase(): CreatePostUseCase

    fun provideFindPostByIdUseCase(): FindPostUseCase

    fun provideAddLikeDislikeUseCase(): AddLikeDislikeUseCase

    fun provideDeletePostUseCase(): DeletePostUseCase
}
