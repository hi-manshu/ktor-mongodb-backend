package com.himanshoe.di.repository

import com.himanshoe.di.service.ServiceProvider
import com.himanshoe.feature.auth.repository.AuthRepository
import com.himanshoe.feature.comment.repository.CommentRepository
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.feature.user.repository.UserRepository

class RepositoryProviderImpl(private val serviceProvider: ServiceProvider) : RepositoryProvider {

    override fun provideAuthRepository(): AuthRepository {
        return RepositoryLocator.provideAuthRepository(
            serviceProvider.provideUserApiService()
        )
    }

    override fun provideUserRepository(): UserRepository {
        return RepositoryLocator.provideUserRepository(serviceProvider.provideUserApiService())
    }

    override fun providePostsRepository(): PostsRepository {
        return RepositoryLocator.providePostsRepository(
            serviceProvider.providePostApiService(),
            serviceProvider.provideUserApiService(),
            serviceProvider.provideCommentApiService()
        )
    }

    override fun provideCommentRepository(): CommentRepository {
        return RepositoryLocator.provideCommentRepository(
            serviceProvider.provideCommentApiService()
        )
    }
}
