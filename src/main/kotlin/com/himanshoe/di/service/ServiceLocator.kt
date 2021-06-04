package com.himanshoe.di.service

import com.himanshoe.posts.Post
import com.himanshoe.posts.service.PostApiService
import com.himanshoe.posts.service.PostApiServiceImpl
import com.himanshoe.user.User
import com.himanshoe.user.service.UserApiService
import com.himanshoe.user.service.UserApiServiceImpl
import org.litote.kmongo.coroutine.CoroutineCollection

object ServiceLocator {

    fun providePostApiService(postCollection: CoroutineCollection<Post>): PostApiService {
        return PostApiServiceImpl(postCollection)
    }

    fun provideUserApiService(userCollection: CoroutineCollection<User>): UserApiService {
        return UserApiServiceImpl(userCollection)
    }

    fun provideServiceProvider(): ServiceProvider {
        return ServiceProviderImpl()
    }
}