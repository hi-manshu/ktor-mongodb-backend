package com.himanshoe.di.service

import com.himanshoe.base.database.redis.RedisClient
import com.himanshoe.feature.comment.Comment
import com.himanshoe.feature.comment.service.CommentApiService
import com.himanshoe.feature.comment.service.CommentApiServiceImpl
import com.himanshoe.feature.posts.Post
import com.himanshoe.feature.posts.service.PostApiService
import com.himanshoe.feature.posts.service.PostApiServiceImpl
import com.himanshoe.feature.user.User
import com.himanshoe.feature.user.service.UserApiService
import com.himanshoe.feature.user.service.UserApiServiceImpl
import org.litote.kmongo.coroutine.CoroutineCollection

object ServiceLocator {

    fun providePostApiService(postCollection: CoroutineCollection<Post>): PostApiService {
        return PostApiServiceImpl(postCollection)
    }

    fun provideUserApiService(userCollection: CoroutineCollection<User>, redisClient: RedisClient): UserApiService {
        return UserApiServiceImpl(userCollection, redisClient)
    }

    fun provideCommentApiService(commentCollection: CoroutineCollection<Comment>): CommentApiService {
        return CommentApiServiceImpl(commentCollection)
    }

    fun provideServiceProvider(): ServiceProvider {
        return ServiceProviderImpl()
    }
}
