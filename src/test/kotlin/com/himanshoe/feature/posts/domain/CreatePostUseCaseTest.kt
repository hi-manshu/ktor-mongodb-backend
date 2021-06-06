package com.himanshoe.feature.posts.domain

import com.himanshoe.MainCoroutineRule
import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.feature.posts.Post
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.runBlockingTest
import com.himanshoe.util.SuccessResponse
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
internal class CreatePostUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockPostsRepository = mock<PostsRepository>()

    private lateinit var createPostUseCase: CreatePostUseCase

    @Before
    fun setup() {
        createPostUseCase = CreatePostUseCase(mockPostsRepository)
    }

    @Test
    fun `should create the post successfully`() {
        return coroutineRule.runBlockingTest {
            // Given
            `given a post should create post successfully`()
            val input = Pair(dummyPost().createdBy, dummyPost())
            // When
            val result = createPostUseCase.invoke(input) as SuccessResponse<Any>
            // Then
            verify(mockPostsRepository).createPost(input.first, input.second)
            MatcherAssert.assertThat(result.data, CoreMatchers.equalTo(true))
        }
    }

    private suspend fun `given a post should create post successfully`() {
        val successResponse = SuccessResponse<Any>(HttpStatusCode.Created, true)
        whenever(mockPostsRepository.createPost(any(), any()))
            .thenReturn(successResponse)
    }

    private fun dummyPost(): Post {
        return Post(
            postId = "12345",
            title = "my post title",
            post = "my post",
            createdBy = "67890"
        )
    }
}
