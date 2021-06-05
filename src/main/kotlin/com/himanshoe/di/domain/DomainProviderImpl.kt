package com.himanshoe.di.domain

import com.himanshoe.di.repository.RepositoryProvider
import com.himanshoe.feature.auth.domain.CreateUserAuthTokenUseCase
import com.himanshoe.feature.auth.domain.LoginUserUseCase
import com.himanshoe.feature.comment.domain.AddCommentUseCase
import com.himanshoe.feature.comment.domain.GetCommentByIdUseCase
import com.himanshoe.feature.comment.domain.GetCommentsUseCase
import com.himanshoe.feature.posts.domain.*
import com.himanshoe.feature.user.domain.CurrentUserDetailUseCase
import com.himanshoe.feature.user.domain.FindUserByIdUseCase
import com.himanshoe.feature.user.domain.GetUserPostsUseCase
import com.himanshoe.feature.user.domain.UpdateCurrentUserUseCase

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

    override fun provideGetUserPostsUseCase(): GetUserPostsUseCase {
        return DomainLocator.provideGetUserPostsUseCase(repositoryProvider.provideUserRepository())
    }

    override fun provideGetPostsUseCase(): GetPostsUseCase {
        return DomainLocator.provideGetPostsUseCase(repositoryProvider.providePostsRepository())
    }

    override fun provideCreatePostUseCase(): CreatePostUseCase {
        return DomainLocator.provideCreatePostUseCase(repositoryProvider.providePostsRepository())
    }

    override fun provideFindPostByIdUseCase(): FindPostUseCase {
        return DomainLocator.provideFindPostUseCase(repositoryProvider.providePostsRepository())
    }

    override fun provideAddLikeDislikeUseCase(): AddLikeDislikeUseCase {
        return DomainLocator.provideAddLikeDislikeUseCase(repositoryProvider.providePostsRepository())
    }

    override fun provideDeletePostUseCase(): DeletePostUseCase {
        return DomainLocator.provideDeletePostUseCase(repositoryProvider.providePostsRepository())
    }

    override fun provideGetCommentsUseCase(): GetCommentsUseCase {
        return DomainLocator.provideGetCommentsUseCase(repositoryProvider.provideCommentRepository())
    }

    override fun provideGetCommentByIdUseCase(): GetCommentByIdUseCase {
        return DomainLocator.provideGetCommentByIdUseCase(repositoryProvider.provideCommentRepository())
    }

    override fun provideAddCommentUseCase(): AddCommentUseCase {
        return DomainLocator.provideAddCommentUseCase(repositoryProvider.provideCommentRepository())
    }
}
