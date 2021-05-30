package com.himanshoe.base.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JwtConfigImpl : JwtConfig {
    companion object {

        private const val SECRET = "zAP5MBA4B4Ijz0MZaS48"
        private const val ISSUER = "ktor.io"
        private const val REALM = "ktor.io"
        private val algorithm = Algorithm.HMAC512(SECRET)
        private const val SUBJECT = "Authentication"
        private const val USER_ID = "userId"
        private const val AUDIENCE = "ktor-mongo"
        private const val ACCESS_TOKEN = 36_000_00 * 24 * 3
    }

    override val verifier: JWTVerifier
        get() = JWT
            .require(algorithm)
            .withIssuer(ISSUER)
            .build()

    override val realm: String
        get() = REALM

    override val userId: String
        get() = USER_ID

    override val audience: String
        get() = AUDIENCE

    override fun makeAccessToken(userId: String): String {
        return encodeJWTWithUserId(userId)
    }

    private fun encodeJWTWithUserId(
        userId: String,
        withExpiresAt: Date = Date(System.currentTimeMillis() + ACCESS_TOKEN)
    ): String = JWT.create()
        .withSubject(SUBJECT)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(USER_ID, userId)
        .sign(algorithm)

    override fun decodeJwtGetUserId(token: String): String {
        return JWT().decodeJwt(token).getClaim(USER_ID).asString()
    }
}