package com.himanshoe.user

import com.himanshoe.user.repository.UserRepository
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.userRoutes(userRepository: UserRepository) {
    routing {
        get<UserInfo> { userRequest ->
            val response = userRepository.findUserById(userRequest.userId)
            call.respond(response)
        }
    }
}