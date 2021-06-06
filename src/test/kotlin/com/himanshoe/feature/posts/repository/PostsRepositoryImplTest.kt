package com.himanshoe.feature.posts.repository

import com.himanshoe.MainCoroutineRule
import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.feature.comment.service.CommentApiService
import com.himanshoe.feature.posts.Post
import com.himanshoe.feature.posts.service.PostApiService
import com.himanshoe.feature.user.service.UserApiService
import com.himanshoe.runBlockingTest
import com.himanshoe.util.Logger
import com.himanshoe.util.SuccessResponse
import com.himanshoe.util.TestData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class PostsRepositoryImplTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockPostApiService by lazy { mock<PostApiService>() }

    private val mockUserApiService by lazy { mock<UserApiService>() }

    private val mockCommentApiService by lazy { mock<CommentApiService>() }

    private val mockExceptionHandler by lazy { mock<ExceptionHandler>() }

    private lateinit var postsRepository: PostsRepositoryImpl

    @Before
    fun setup() {
        postsRepository = PostsRepositoryImpl(
            mockPostApiService,
            mockUserApiService,
            mockCommentApiService,
            mockExceptionHandler
        )
    }

    @Test
    fun `should create post with a specific post`() {
        return coroutineRule.runBlockingTest {
            `given successful create post`()
            // given
            val post = TestData.dummyPost()
            // when
            val result = postsRepository.createPost(TestData.dummyPostId(), post) as SuccessResponse<Any>
            // then
            assertThat(result.data, equalTo(true))
        }
    }

    private suspend fun `given successful create post`() {
        val response = SuccessResponse<Any>(HttpStatusCode.Created, true)
        whenever(postsRepository.createPost(any(), any()))
            .thenReturn(response)
    }
}
