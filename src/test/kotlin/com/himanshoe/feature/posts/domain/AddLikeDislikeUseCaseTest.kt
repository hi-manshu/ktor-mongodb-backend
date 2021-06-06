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
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AddLikeDislikeUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockPostsRepository = mock<PostsRepository>()

    private lateinit var addLikeDislikeUseCase: AddLikeDislikeUseCase

    @Before
    fun setup() {
        addLikeDislikeUseCase = AddLikeDislikeUseCase(mockPostsRepository)
    }

    @Test
    fun `should add like successfully`() {
        return coroutineRule.runBlockingTest {
            `given a post should add dislike successfully`()
            // given
            val input = Triple("12345", "67890", true)
            // when
            val result = addLikeDislikeUseCase.invoke(input) as SuccessResponse<Any>
            // then
            verify(mockPostsRepository).likeDislikePost(input.first, input.second, input.third)
            MatcherAssert.assertThat(result.statusCode, CoreMatchers.equalTo(HttpStatusCode.OK))
        }
    }

    @Test
    fun `should add dislike successfully`() {
        return coroutineRule.runBlockingTest {
            `given a post should add like successfully`()
            // given
            val input = Triple("12345", "67890", true)
            // when
            val result = addLikeDislikeUseCase.invoke(input) as SuccessResponse<Any>
            // then
            verify(mockPostsRepository).likeDislikePost(input.first, input.second, input.third)
            MatcherAssert.assertThat(result.statusCode, CoreMatchers.equalTo(HttpStatusCode.OK))
        }
    }

    private suspend fun `given a post should add dislike successfully`() {
        val successResponse = SuccessResponse<Any>(HttpStatusCode.OK, true)
        whenever(mockPostsRepository.likeDislikePost(any(), any(), any()))
            .thenReturn(successResponse)
    }

    private suspend fun `given a post should add like successfully`() {
        val successResponse = SuccessResponse<Any>(HttpStatusCode.OK, true)
        whenever(mockPostsRepository.likeDislikePost(any(), any(), any()))
            .thenReturn(successResponse)
    }
}
