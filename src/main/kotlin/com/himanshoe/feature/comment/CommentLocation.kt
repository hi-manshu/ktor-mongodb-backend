package com.himanshoe.feature.comment

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(CommentConstant.COMMENTS)
data class CommentsList(val page: Int, val count: Int)
