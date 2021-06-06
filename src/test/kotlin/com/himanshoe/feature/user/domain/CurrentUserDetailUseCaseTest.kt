package com.himanshoe.feature.user.domain

import com.himanshoe.MainCoroutineRule
import com.himanshoe.feature.user.repository.UserRepository
import com.himanshoe.runBlockingTest
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CurrentUserDetailUseCaseTest {
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockUserRepository = mock<UserRepository>()

    private lateinit var currentUserDetailUseCase: CurrentUserDetailUseCase

    @Before
    fun setup() {
        currentUserDetailUseCase = CurrentUserDetailUseCase(mockUserRepository)
    }

    @Test
    fun `should fetch user detail when user id is provided`(){
        return coroutineRule.runBlockingTest {

        }
    }
}
