package com.himanshoe.posts.repository

import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.posts.Posts
import com.himanshoe.util.BaseResponse
import com.himanshoe.util.PaginatedResponse
import io.ktor.http.*
import org.litote.kmongo.coroutine.CoroutineCollection

class PostsRepositoryImpl(
    private val postCollection: CoroutineCollection<Posts>,
    private val exceptionHandler: ExceptionHandler
) : PostsRepository {

    companion object {
        private const val PLEASE_CHECK_THE_PARAMS = "Please check the query params"
    }

    override suspend fun fetchPosts(page: Int, count: Int): BaseResponse<Any> {
        if (page > 0 && count > 0) {
            val skips = page * count
            val response = postCollection.find().skip(skips).limit(count)
            val results: List<Posts> = response.toList().sortedBy {
                it.createdAt
            }
            val totalCount = postCollection.estimatedDocumentCount().toInt()
            val totalPages = (totalCount.div(count)).plus(1).toInt()
            val next = if (results.isNotEmpty()) page + 1 else null
            val prev = if (page > 0) page - 1 else null
            return PaginatedResponse(statusCode = HttpStatusCode.OK, prev, next, totalCount, totalPages, results)
        } else {
            throw exceptionHandler.respondWithGenericException(PLEASE_CHECK_THE_PARAMS)
        }
    }

}