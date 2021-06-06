package com.himanshoe.feature.posts.domain

import com.himanshoe.MainCoroutineRule
import com.himanshoe.feature.posts.repository.PostsRepository
import com.himanshoe.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class FindPostUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockPostRepository = mock<PostsRepository>()

    @Test
    fun `should test for single post`() {
        return coroutineRule.runBlockingTest {
            val firstPostUseCase = FindPostUseCase(mockPostRepository)
            firstPostUseCase.invoke("12345")
            verify(mockPostRepository).findPostById("12345")
        }
    }
}
