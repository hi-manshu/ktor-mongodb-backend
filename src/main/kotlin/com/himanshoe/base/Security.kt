package com.himanshoe.base

import com.himanshoe.base.auth.JwtConfig
import com.himanshoe.base.auth.UserPrincipal
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureSecurity(jwtConfig: JwtConfig) {
    authentication {
        jwt {
            verifier(jwtConfig.verifier)
            realm = jwtConfig.realm
            validate { credential ->
                if (credential.payload.audience.contains(jwtConfig.audience)) {
                    val userId = credential.payload.getClaim(jwtConfig.userId).asString()
                    if (!userId.isNullOrBlank()) {
                        log.info("Validate success")
                        JWTPrincipal(credential.payload)
                        UserPrincipal(userId)
                    } else {
                        log.error("Validate error")
                        null
                    }
                } else null
            }
        }
    }
}

val ApplicationCall.user
    get() = authentication.principal<UserPrincipal>()

