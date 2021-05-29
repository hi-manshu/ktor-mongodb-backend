package com.himanshoe

import com.himanshoe.base.configureHTTP
import com.himanshoe.base.configureRoutingAndSerialization
import com.himanshoe.base.configureSecurity
import com.himanshoe.base.configureStatusPages
import com.himanshoe.di.ServiceLocator
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

/**
 * Please note that you can use any other name instead of *module*.
 * Also note that you can have more then one modules in your application.
 * */

private val serviceLocator = ServiceLocator

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    configureStatusPages()
    configureRoutingAndSerialization(serviceLocator)
    configureSecurity(serviceLocator.provideJwtConfig())
    configureHTTP()
}
