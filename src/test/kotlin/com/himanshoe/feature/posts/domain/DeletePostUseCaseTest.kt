package com.himanshoe.feature.posts.domain

import com.himanshoe.MainCoroutineRule
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.runBlockingTest
import com.himanshoe.util.SuccessResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DeletePostUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockPostsRepository = mock<PostsRepository>()

    private lateinit var deletePostUseCase: DeletePostUseCase

    private val input = Pair("12345", "67890")

    @Before
    fun setup() {
        deletePostUseCase = DeletePostUseCase(mockPostsRepository)
    }

    @Test
    fun `should delete the post with a specific postId`() {
        return coroutineRule.runBlockingTest {
            // Given
            `given successful delete post`()
            // When
            val result = deletePostUseCase.invoke(input) as SuccessResponse<Any>
            // Then
            verify(mockPostsRepository).deletePost(input.first, input.second)
            assertThat(result.data, equalTo(true))
        }
    }

    @Test
    fun `should delete the post with a specific postId and return false`() {
        return coroutineRule.runBlockingTest {
            // Given
            `given unsuccessful delete post`()
            // when
            val result = deletePostUseCase.invoke(input) as SuccessResponse<Any>
            // Then
            verify(mockPostsRepository).deletePost(input.first, input.second)
            assertThat(result.data, equalTo(false))
        }
    }

    private suspend fun `given successful delete post`() {
        val successResponse = SuccessResponse<Any>(HttpStatusCode.OK, true)
        whenever(mockPostsRepository.deletePost(any(), any()))
            .thenReturn(successResponse)
    }

    private suspend fun `given unsuccessful delete post`() {
        val successResponse = SuccessResponse<Any>(HttpStatusCode.OK, false)
        whenever(mockPostsRepository.deletePost(any(), any()))
            .thenReturn(successResponse)
    }
}
