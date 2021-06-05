package com.himanshoe.di.service

import com.himanshoe.feature.comment.service.CommentApiService
import com.himanshoe.feature.posts.service.PostApiService
import com.himanshoe.feature.user.service.UserApiService

interface ServiceProvider {

    fun providePostApiService(): PostApiService

    fun provideUserApiService(): UserApiService

    fun provideCommentApiService(): CommentApiService
}
