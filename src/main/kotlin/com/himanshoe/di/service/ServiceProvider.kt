package com.himanshoe.di.service

import com.himanshoe.feature.posts.service.PostApiService
import com.himanshoe.feature.user.service.UserApiService

interface ServiceProvider {

    fun providePostApiService(): PostApiService

    fun provideUserApiService(): UserApiService
}
