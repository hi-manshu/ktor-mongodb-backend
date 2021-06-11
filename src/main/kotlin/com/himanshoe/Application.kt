package com.himanshoe

import com.himanshoe.base.configureRoutingAndSerialization
import com.himanshoe.base.configureSecurity
import com.himanshoe.base.configureStatusPages
import com.himanshoe.base.http.configureHTTP
import com.himanshoe.di.ConfigLocator
import com.himanshoe.di.RedisLocator
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

/**
 * Please note that you can use any other name instead of *module*.
 * Also note that you can have more then one modules in your application.
 * */

private val serviceLocator = ConfigLocator

fun Application.module() {
    ConfigLocator.provideJwtConfig()
    configureStatusPages()
    configureSecurity()
    configureRoutingAndSerialization()
    configureHTTP()
}
