package com.himanshoe.di.database

import com.himanshoe.base.database.Database
import com.himanshoe.base.database.DatabaseImpl

object DatabaseLocator {

    private fun provideClientName(): String {
        return "ktor-mongo"
    }

    fun provideDatabase(): Database {
        return DatabaseImpl(provideClientName())
    }
}