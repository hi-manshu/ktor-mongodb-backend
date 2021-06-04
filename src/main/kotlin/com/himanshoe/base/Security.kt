package com.himanshoe.base

import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.base.auth.UserIdPrincipalForUser
import io.ktor.application.*
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt

fun Application.configureSecurity() {
    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.ClAIM).asString()
                if (claim != null) {
                    UserIdPrincipalForUser(claim)
                } else {
                    null
                }
            }
        }
    }
}
