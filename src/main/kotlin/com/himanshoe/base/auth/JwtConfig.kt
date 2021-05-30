package com.himanshoe.base.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

/**
 * Simple implementation for providing JWT Authentication mechanism.
 * Use [makeAccessToken] method to generate token.
 */
open class JwtConfig private constructor(secret: String) {

    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    /**
     * Generates JWT token from [userId].
     */
    fun makeAccessToken(userId: String): String = JWT
        .create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(ClAIM, userId)
        .sign(algorithm)

    companion object {
        lateinit var instance: JwtConfig
            private set

        fun initialize(secret: String) {
            synchronized(this) {
                if (!this::instance.isInitialized) {
                    instance = JwtConfig(secret)
                }
            }
        }

        private const val ISSUER = "ktor-backend"
        private const val AUDIENCE = "ktor-backend"
        const val ClAIM = "userId"
    }
}
