package com.himanshoe.base.database.redis

interface RedisClient {

    fun write(key: String?, value: Any)

    fun read(key: String, response: (Any?) -> Unit)

    fun contains(key: String, response: (Boolean) -> Unit)
}
