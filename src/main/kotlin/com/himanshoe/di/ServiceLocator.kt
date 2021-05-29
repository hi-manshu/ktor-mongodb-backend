package com.himanshoe.di

import com.himanshoe.auth.repository.AuthRepository
import com.himanshoe.auth.repository.AuthRepositoryImpl
import com.himanshoe.base.Database

object ServiceLocator {

    fun authRepository(): AuthRepository {
        return AuthRepositoryImpl(Database.userCollection)
    }

}