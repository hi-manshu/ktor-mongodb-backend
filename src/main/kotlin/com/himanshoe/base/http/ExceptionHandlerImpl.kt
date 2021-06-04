package com.himanshoe.base.http

import com.himanshoe.base.BadRequestException
import com.himanshoe.base.AuthorizationException
import com.himanshoe.base.NotFoundException
import com.himanshoe.base.ConflictException
import com.himanshoe.base.SomethingWentWrongException

/**
 * Handles the Exceptions and implements the interface [ExceptionHandler]
 */
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

    override fun respondWithGenericException(message: String?): Exception {
        return SomethingWentWrongException(message)
    }

    override fun respondWithSomethingWentWrongException(message: String): Exception {
        return SomethingWentWrongException(message)
    }
}
