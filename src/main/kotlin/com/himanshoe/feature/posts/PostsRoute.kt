package com.himanshoe.feature.posts

import com.himanshoe.di.domain.DomainProvider
import com.himanshoe.feature.posts.request.LikeDislikeRequest
import com.himanshoe.util.getBodyContent
import com.himanshoe.util.getUserId
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.locations.get
import io.ktor.locations.delete
import io.ktor.locations.post
import io.ktor.locations.put
import io.ktor.response.respond
import io.ktor.routing.routing

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
