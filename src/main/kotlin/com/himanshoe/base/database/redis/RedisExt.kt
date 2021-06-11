package com.himanshoe.base.database.redis

import com.himanshoe.base.asClosableJob
import com.himanshoe.util.asString
import com.himanshoe.util.readFromString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import redis.clients.jedis.Jedis

fun Jedis.setAsync(key: String?, value: Any) {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        set(key, value.asString())
    }.asClosableJob()
}

fun Jedis.getAsync(key: String, response: (Any) -> Unit) {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        val value: String = withContext(Dispatchers.IO) {
            return@withContext get(key)
        }
        response(value.readFromString())
    }.asClosableJob()
}

fun Jedis.contains(key: String, response: (Boolean) -> Unit) {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        val value: String = withContext(Dispatchers.IO) {
            return@withContext get(key)
        }
        response(exists(key))
    }.asClosableJob()
}
