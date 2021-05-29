package com.himanshoe.base

import com.himanshoe.base.auth.JwtConfig
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureSecurity(jwtConfig: JwtConfig) {

    authentication {
        jwt {
            verifier(jwtConfig.verifier)
            realm = jwtConfig.realm
            validate {
                val playerId = it.payload.getClaim(jwtConfig.userId).asString()
                if (playerId != null) {
                    UserIdPrincipal(playerId)
                } else {
                    null
                }
            }
        }
    }
}
