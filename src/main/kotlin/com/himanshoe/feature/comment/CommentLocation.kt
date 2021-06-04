package com.himanshoe.feature.comment

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(CommentConstant.Comments)
data class CommentsList(val page: Int, val count: Int)
