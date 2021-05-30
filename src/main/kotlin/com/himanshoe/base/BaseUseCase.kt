package com.himanshoe.base

import com.himanshoe.util.BaseResponse

/** A Use Case that takes an argument and returns a result. */
interface BaseUseCase<in I, out R : Any> {
    /** Executes this use case with given input. */
    suspend operator fun invoke(input: I): BaseResponse<R>
}