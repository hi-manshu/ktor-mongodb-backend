package com.himanshoe.base.auth

import com.auth0.jwt.JWTVerifier

interface JwtConfig {

    val verifier: JWTVerifier

    val realm: String

    val userId: String

    fun makeAccessToken(userId: String): String

    fun decodeJwtGetUserId(token: String): String

}