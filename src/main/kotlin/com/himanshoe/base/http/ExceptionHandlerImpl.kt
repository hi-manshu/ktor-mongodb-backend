package com.himanshoe.base.http

import com.himanshoe.base.AuthorizationException
import com.himanshoe.base.BadRequestException
import com.himanshoe.base.ConflictException
import com.himanshoe.base.NotFoundException

class ExceptionHandlerImpl : ExceptionHandler {

    override fun respondWithBadRequestException(message: String?): Exception {
        return BadRequestException(message)
    }

    override fun respondWithUnauthorizedException(message: String?): Exception {
        return AuthorizationException(message)
    }

    override fun respondWithNotFoundException(message: String?): Exception {
        return NotFoundException(message)
    }

    override fun respondWithAlreadyExistException(message: String?): Exception {
        return ConflictException(message)
    }
}