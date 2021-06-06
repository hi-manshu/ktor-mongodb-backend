package com.himanshoe.feature.posts.domain

import com.himanshoe.MainCoroutineRule
import com.himanshoe.feature.posts.repository.PostsRepository
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
internal class AddLikeDislikeUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockPostsRepository = mock<PostsRepository>()

    private lateinit var addLikeDislikeUseCase: AddLikeDislikeUseCase

    @Before
    fun setup() {
        addLikeDislikeUseCase = AddLikeDislikeUseCase(mockPostsRepository)
    }
}
