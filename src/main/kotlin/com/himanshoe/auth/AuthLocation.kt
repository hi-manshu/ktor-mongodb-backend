package com.himanshoe.auth

import io.ktor.locations.*

@Location(AuthConstant.LOGIN)
data class AuthLogin(val username: String, val password: String)

@Location(AuthConstant.REGISTER)
data class AuthRegister(val username: String, val password: String)
