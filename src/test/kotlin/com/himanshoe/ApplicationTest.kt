package com.himanshoe

import com.himanshoe.base.configureRoutingAndSerialization
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ configureRoutingAndSerialization(serviceLocator) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }
}