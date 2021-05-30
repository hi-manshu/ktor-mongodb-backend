package com.himanshoe.di.repository

import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.user.repository.UserRepository

interface RepositoryProvider {

    fun provideAuthRepository(): AuthRepository

    fun provideUserRepository(): UserRepository
}