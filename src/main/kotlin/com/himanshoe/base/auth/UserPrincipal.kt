package com.himanshoe.base.auth

import io.ktor.auth.*

data class UserPrincipal(val userId: String? = null, val other: String = "default") : Principal

