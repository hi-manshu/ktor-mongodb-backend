package com.himanshoe.di.domain

import com.himanshoe.di.repository.RepositoryLocator
import com.himanshoe.feature.auth.domain.CreateUserAuthTokenUseCase
import com.himanshoe.feature.auth.domain.LoginUserUseCase
import com.himanshoe.feature.auth.repository.AuthRepository
import com.himanshoe.feature.comment.domain.AddCommentUseCase
import com.himanshoe.feature.comment.domain.GetCommentByIdUseCase
import com.himanshoe.feature.comment.domain.GetCommentsByPostIdUseCase
import com.himanshoe.feature.comment.domain.GetCommentsUseCase
import com.himanshoe.feature.comment.repository.CommentRepository
import com.himanshoe.feature.posts.domain.*
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.feature.user.domain.CurrentUserDetailUseCase
import com.himanshoe.feature.user.domain.FindUserByIdUseCase
import com.himanshoe.feature.user.domain.GetUserPostsUseCase
import com.himanshoe.feature.user.domain.UpdateCurrentUserUseCase
import com.himanshoe.feature.user.repository.UserRepository

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
     * [provideGetUserPostsUseCase] provides the [GetUserPostsUseCase] instance to [DomainProvider]
     */
    fun provideGetPostsUseCase(postsRepository: PostsRepository): GetPostsUseCase {
        return GetPostsUseCase(postsRepository)
    }

    /**
     * [provideCreatePostUseCase] provides the [CreatePostUseCase] instance to [DomainProvider]
     */
    fun provideCreatePostUseCase(postsRepository: PostsRepository): CreatePostUseCase {
        return CreatePostUseCase(postsRepository)
    }

    /**
     * [provideFindPostUseCase] provides the [FindPostUseCase] instance to [DomainProvider]
     */
    fun provideFindPostUseCase(postsRepository: PostsRepository): FindPostUseCase {
        return FindPostUseCase(postsRepository)
    }

    /**
     * [provideCreatePostUseCase] provides the [AddLikeDislikeUseCase] instance to [DomainProvider]
     */
    fun provideAddLikeDislikeUseCase(postsRepository: PostsRepository): AddLikeDislikeUseCase {
        return AddLikeDislikeUseCase(postsRepository)
    }

    /**
     * [provideCreatePostUseCase] provides the [AddLikeDislikeUseCase] instance to [DomainProvider]
     */
    fun provideDeletePostUseCase(postsRepository: PostsRepository): DeletePostUseCase {
        return DeletePostUseCase(postsRepository)
    }

    /**
     * [provideAddCommentUseCase] provides the [AddCommentUseCase] instance to [DomainProvider]
     */
    fun provideAddCommentUseCase(commentRepository: CommentRepository): AddCommentUseCase {
        return AddCommentUseCase(commentRepository)
    }

    /**
     * [provideGetCommentByIdUseCase] provides the [AddCommentUseCase] instance to [DomainProvider]
     */
    fun provideGetCommentByIdUseCase(commentRepository: CommentRepository): GetCommentByIdUseCase {
        return GetCommentByIdUseCase(commentRepository)
    }

    /**
     * [provideGetCommentByIdUseCase] provides the [AddCommentUseCase] instance to [DomainProvider]
     */
    fun provideGetCommentsUseCase(commentRepository: CommentRepository): GetCommentsUseCase {
        return GetCommentsUseCase(commentRepository)
    }
    /**
     * [provideGetCommentByIdUseCase] provides the [AddCommentUseCase] instance to [DomainProvider]
     */
    fun provideGetCommentsByPostIdUseCase(commentRepository: CommentRepository): GetCommentsByPostIdUseCase {
        return GetCommentsByPostIdUseCase(commentRepository)
    }

    /**
     * [provideDomainProvider] provides the [DomainProvider] instance
     */
    fun provideDomainProvider(): DomainProvider {
        return DomainProviderImpl(RepositoryLocator.provideRepositoryProvider())
    }
}
