package com.himanshoe.util

object Logger {

    fun d(message: String? = null) {
        println("Ktor-backend-logs ------ Start")
        println(message)
        println("Ktor-backend-logs ------ End")
    }
}