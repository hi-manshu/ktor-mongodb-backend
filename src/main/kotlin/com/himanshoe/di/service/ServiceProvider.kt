package com.himanshoe.di.service

import com.himanshoe.posts.service.PostApiService
import com.himanshoe.user.service.UserApiService

interface ServiceProvider {

    fun providePostApiService(): PostApiService

    fun provideUserApiService(): UserApiService

}