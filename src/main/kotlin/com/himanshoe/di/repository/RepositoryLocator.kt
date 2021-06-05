package com.himanshoe.di.repository

import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.di.ConfigLocator
import com.himanshoe.di.service.ServiceLocator
import com.himanshoe.feature.auth.repository.AuthRepository
import com.himanshoe.feature.auth.repository.AuthRepositoryImpl
import com.himanshoe.feature.comment.repository.CommentRepository
import com.himanshoe.feature.comment.repository.CommentRepositoryImpl
import com.himanshoe.feature.comment.service.CommentApiService
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.feature.posts.repository.PostsRepositoryImpl
import com.himanshoe.feature.posts.service.PostApiService
import com.himanshoe.feature.user.repository.UserRepository
import com.himanshoe.feature.user.repository.UserRepositoryImpl
import com.himanshoe.feature.user.service.UserApiService

object RepositoryLocator {

    fun provideAuthRepository(userApiService: UserApiService): AuthRepository {
        return AuthRepositoryImpl(
            userApiService,
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

    fun provideCommentRepository(commentApiService: CommentApiService): CommentRepository {
        return CommentRepositoryImpl(
            commentApiService,
            ConfigLocator.provideExceptionHandler()
        )
    }

    fun provideRepositoryProvider(): RepositoryProvider {
        return RepositoryProviderImpl(ServiceLocator.provideServiceProvider())
    }
}
