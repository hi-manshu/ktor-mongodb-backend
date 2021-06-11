package com.himanshoe.di

import com.himanshoe.base.database.redis.RedisClient
import com.himanshoe.base.database.redis.RedisClientImpl
import redis.clients.jedis.Jedis

object RedisLocator {

    fun provideRedisInstance(): Jedis {
        return Jedis("localhost", 8081)
    }

    fun provideRedisClient(): RedisClient {
        return RedisClientImpl(provideRedisInstance())
    }
}
