package com.himanshoe.feature.comment

import com.himanshoe.di.domain.DomainProvider
import com.himanshoe.feature.comment.request.AddCommentRequest
import com.himanshoe.feature.comment.request.FetchCommentsRequest
import com.himanshoe.util.getBodyContent
import com.himanshoe.util.getUserId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.response.*
import io.ktor.routing.*

fun Application.commentRoutes(domainProvider: DomainProvider) {

    routing {

        authenticate {
            post<AddComment> { request ->
                val userId = getUserId()
                val comment = getBodyContent<Comment>()
                val commentRequest = AddCommentRequest(userId, request.postId, comment)
                val response = domainProvider.provideAddCommentUseCase().invoke(commentRequest)
                call.respond(response)
            }

            get<IndividualComment> { request ->
                val commentId = request.commentId
                val response = domainProvider.provideGetCommentByIdUseCase().invoke(commentId)
                call.respond(response)
            }
            get<CommentsList> { request ->
                val response = domainProvider.provideGetCommentsUseCase().invoke(FetchCommentsRequest(request.page, request.count))
                call.respond(response)
            }
        }
    }
}
