package com.himanshoe.di.repository

import com.himanshoe.feature.auth.repository.AuthRepository
import com.himanshoe.feature.comment.repository.CommentRepository
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.feature.user.repository.UserRepository

interface RepositoryProvider {

    fun provideAuthRepository(): AuthRepository

    fun provideUserRepository(): UserRepository

    fun providePostsRepository(): PostsRepository

    fun provideCommentRepository(): CommentRepository
}
