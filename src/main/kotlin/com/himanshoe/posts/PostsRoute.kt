package com.himanshoe.posts

import com.himanshoe.di.domain.DomainProvider
import com.himanshoe.posts.request.LikeDislikeRequest
import com.himanshoe.util.getBodyContent
import com.himanshoe.util.getUserId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.locations.put
import io.ktor.response.*
import io.ktor.routing.*

fun Application.postsRoute(domainProvider: DomainProvider) {

    routing {

        get<PostsList> { request ->
            val response = domainProvider.provideGetPostsUseCase().invoke(Pair(request.page, request.count))
            call.respond(response)
        }

        get<IndividualPost> { request ->
            val postId = request.postId
            val response = domainProvider.provideFindPostByIdUseCase().invoke(postId)
            call.respond(response)
        }

        authenticate {

            post<CreatePost> {
                val userId = getUserId()
                val post = getBodyContent<Post>()
                val response = domainProvider.provideCreatePostUseCase().invoke(Pair(userId, post))
                call.respond(response)
            }
            put<LikeDislikePost> {
                val userId = getUserId()
                val post = getBodyContent<LikeDislikeRequest>()
                val request = Triple(userId, post.postId, post.liked) as Triple<String, String, Boolean>
                val response = domainProvider.provideAddLikeDislikeUseCase().invoke(request)
                call.respond(response)
            }

            delete<IndividualPost> { request ->
                val userId = getUserId()
                val postId = request.postId
                val response =
                    domainProvider.provideDeletePostUseCase().invoke(Pair(userId, postId) as Pair<String, String>)
                call.respond(response)
            }
        }
    }
}
