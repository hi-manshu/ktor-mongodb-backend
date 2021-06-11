package com.himanshoe.di.database

import com.himanshoe.base.database.mongo.Database
import com.himanshoe.base.database.mongo.DatabaseImpl

object DatabaseLocator {

    private fun provideClientName(): String {
        return "ktor-mongo"
    }

    fun provideDatabase(): Database {
        return DatabaseImpl(provideClientName())
    }
}
