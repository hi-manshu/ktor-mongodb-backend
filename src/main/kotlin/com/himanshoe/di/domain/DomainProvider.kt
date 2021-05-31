package com.himanshoe.di.domain

import com.himanshoe.auth.domain.CreateUserAuthTokenUseCase
import com.himanshoe.auth.domain.LoginUserUseCase
import com.himanshoe.posts.domain.AddLikeDislikeUseCase
import com.himanshoe.posts.domain.CreatePostUseCase
import com.himanshoe.posts.domain.GetPostsUseCase
import com.himanshoe.user.domain.CurrentUserDetailUseCase
import com.himanshoe.user.domain.FindUserByIdUseCase
import com.himanshoe.user.domain.GetUserPostsUseCase
import com.himanshoe.user.domain.UpdateCurrentUserUseCase

interface DomainProvider {

    fun provideCreateUserAuthTokenUseCase(): CreateUserAuthTokenUseCase

    fun provideLoginUserUseCase(): LoginUserUseCase

    fun provideFindUserByIdUseCase(): FindUserByIdUseCase

    fun provideCurrentUserDetailUseCase(): CurrentUserDetailUseCase

    fun provideUpdateCurrentUserUseCase(): UpdateCurrentUserUseCase

    fun provideGetUserPostsUseCase(): GetUserPostsUseCase

    fun provideGetPostsUseCase(): GetPostsUseCase

    fun provideCreatePostUseCase(): CreatePostUseCase

    fun provideAddLikeDislikeUseCase(): AddLikeDislikeUseCase
}