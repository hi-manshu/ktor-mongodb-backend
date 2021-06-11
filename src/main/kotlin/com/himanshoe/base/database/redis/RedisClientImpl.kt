package com.himanshoe.base.database.redis

import redis.clients.jedis.Jedis

class RedisClientImpl(private val instance: Jedis ) : RedisClient {


    override fun write(key: String?, value: Any) {
        instance.setAsync(key, value)
    }

    override fun read(key: String, response: (Any?) -> Unit) {
        contains(key) { isPresent ->
            if (isPresent) {
                instance.getAsync(key, response)
            } else {
                response(null)
            }
        }
    }

    override fun contains(key: String, response: (Boolean) -> Unit) {
        instance.contains(key, response)
    }
}
