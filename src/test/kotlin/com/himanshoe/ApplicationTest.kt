package com.himanshoe

import io.ktor.http.*
import com.himanshoe.plugins.configureRouting
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }
}