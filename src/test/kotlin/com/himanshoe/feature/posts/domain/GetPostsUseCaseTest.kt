package com.himanshoe.feature.posts.domain

import com.himanshoe.MainCoroutineRule
import com.himanshoe.feature.posts.PostList
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.runBlockingTest
import com.himanshoe.util.PaginatedResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPostsUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var getPostsUseCase: GetPostsUseCase

    private val mockPostRepository = mock<PostsRepository>()

    private val input = Pair(1, 10)

    @Before
    fun setup() {
        getPostsUseCase = GetPostsUseCase(mockPostRepository)
    }

    @Test
    fun `should return list of post when given page number and count`() {
        return coroutineRule.runBlockingTest {
            `given successful post list`()
            val result = getPostsUseCase.invoke(input) as PaginatedResponse<Any>
            verify(mockPostRepository).fetchPosts(1, 10)
            MatcherAssert.assertThat(result.data, CoreMatchers.equalTo(dummyListOfPostList()))
        }
    }

    private suspend fun `given successful post list`() {
        val response =
            PaginatedResponse<Any>(statusCode = HttpStatusCode.OK, null, null, 1, 1, data = dummyListOfPostList())
        whenever(mockPostRepository.fetchPosts(any(), any()))
            .thenReturn(response)
    }

    private fun dummyPostList(): PostList {
        return PostList(
            postId = "12345",
            title = "my post title",
            post = "my post"
        )
    }

    private fun dummyListOfPostList(): List<PostList> {
        return listOf(dummyPostList())
    }
}
