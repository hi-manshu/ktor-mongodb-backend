package com.himanshoe.base

import com.himanshoe.base.auth.JwtConfig
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureSecurity() {
    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.ClAIM).asString()
                com.himanshoe.util.Logger.d(claim)
                if (claim != null) {
                    UserIdPrincipal(claim)
                } else {
                    null
                }
            }
        }
    }
}

