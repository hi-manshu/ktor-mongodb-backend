package com.himanshoe.feature.posts.repository

import com.himanshoe.MainCoroutineRule
import com.himanshoe.base.http.ExceptionHandler
import com.himanshoe.feature.comment.service.CommentApiService
import com.himanshoe.feature.posts.Post
import com.himanshoe.feature.posts.service.PostApiService
import com.himanshoe.feature.user.service.UserApiService
import com.himanshoe.runBlockingTest
import com.himanshoe.util.SuccessResponse
import com.himanshoe.util.TestData
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PostsRepositoryImplTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockPostApiService = mock<PostApiService>()

    private val mockUserApiService = mock<UserApiService>()

    private val mockCommentApiService = mock<CommentApiService>()

    private val mockExceptionHandler = mock<ExceptionHandler>()

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
            val post = dummyPost()
            // when
            val result = postsRepository.createPost(dummyPost().postId, post) as SuccessResponse<Any>
            // then
            assertThat(result.data, equalTo(true))
        }
    }

    private suspend fun `given successful create post`() {
        val response = SuccessResponse<Any>(HttpStatusCode.Created, true)
        whenever(postsRepository.createPost(any(), any()))
            .thenReturn(response)
    }

    private fun dummyPost(): Post {
        return Post(
            postId = "12345",
            title = "my post title",
            post = "my post"
        )
    }
}
