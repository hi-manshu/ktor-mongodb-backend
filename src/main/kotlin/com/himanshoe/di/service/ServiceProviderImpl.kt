package com.himanshoe.di.service

import com.himanshoe.di.RedisLocator
import com.himanshoe.di.database.DatabaseLocator
import com.himanshoe.feature.comment.service.CommentApiService
import com.himanshoe.feature.posts.service.PostApiService
import com.himanshoe.feature.user.service.UserApiService

class ServiceProviderImpl : ServiceProvider {

    override fun providePostApiService(): PostApiService {
        return ServiceLocator.providePostApiService(DatabaseLocator.provideDatabase().postCollection)
    }

    override fun provideUserApiService(): UserApiService {
        return ServiceLocator.provideUserApiService(
            DatabaseLocator.provideDatabase().userCollection,
            RedisLocator.provideRedisClient()
        )
    }

    override fun provideCommentApiService(): CommentApiService {
        return ServiceLocator.provideCommentApiService(DatabaseLocator.provideDatabase().commentCollection)
    }
}
