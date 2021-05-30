package com.himanshoe.base.auth

import io.ktor.auth.*

data class UserIdPrincipalForUser(val userId: String) : Principal

