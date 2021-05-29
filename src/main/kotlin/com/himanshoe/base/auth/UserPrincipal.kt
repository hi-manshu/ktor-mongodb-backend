package com.himanshoe.base.auth

import io.ktor.auth.*

data class UserPrincipal(val userId: String? = null) : Principal
