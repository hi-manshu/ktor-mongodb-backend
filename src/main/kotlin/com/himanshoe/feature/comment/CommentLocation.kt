package com.himanshoe.feature.comment

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location(CommentConstant.COMMENTS)
data class CommentsList(val page: Int, val count: Int)

@KtorExperimentalLocationsAPI
@Location(CommentConstant.COMMENT)
data class AddComment(val postId: String)

@KtorExperimentalLocationsAPI
@Location(CommentConstant.COMMENT)
data class IndividualComment(val commentId: String)
