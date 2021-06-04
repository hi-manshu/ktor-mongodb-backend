package com.himanshoe.auth

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(AuthConstant.LOGIN)
class LoginUser

@KtorExperimentalLocationsAPI
@Location(AuthConstant.REGISTER)
class RegisterUser
