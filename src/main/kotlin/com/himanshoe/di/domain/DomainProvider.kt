package com.himanshoe.di.domain

import com.himanshoe.feature.auth.domain.CreateUserAuthTokenUseCase
import com.himanshoe.feature.auth.domain.LoginUserUseCase
import com.himanshoe.feature.comment.domain.AddCommentUseCase
import com.himanshoe.feature.comment.domain.GetCommentByIdUseCase
import com.himanshoe.feature.comment.domain.GetCommentsByPostIdUseCase
import com.himanshoe.feature.comment.domain.GetCommentsUseCase
import com.himanshoe.feature.comment.repository.CommentRepository
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

    fun provideGetCommentsUseCase(): GetCommentsUseCase

    fun provideGetCommentByIdUseCase(): GetCommentByIdUseCase

    fun provideAddCommentUseCase(): AddCommentUseCase

    fun provideGetCommentsByPostIdUseCase(): GetCommentsByPostIdUseCase
}
