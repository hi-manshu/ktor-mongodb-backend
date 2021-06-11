package com.himanshoe.base

import kotlinx.coroutines.Job

class ClosableJob(job: Job) : Job by job, Closable {
    override fun close() {
        cancel()
    }
}

fun Job.asClosableJob(): ClosableJob = ClosableJob(this)

fun Job.asClosable(): Closable = ClosableJob(this)

interface Closable {
    fun close()
}
