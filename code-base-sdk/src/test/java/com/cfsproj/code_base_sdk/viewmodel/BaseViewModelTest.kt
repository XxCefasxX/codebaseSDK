package com.cfsproj.code_base_sdk.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cfsproj.code_base_sdk.domain.DomainChar
import com.cfsproj.code_base_sdk.usecases.CharactersUseCase
import com.cfsproj.code_base_sdk.utils.AppType
import com.cfsproj.code_base_sdk.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BaseViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockUseCase = mockk<CharactersUseCase>(relaxed = true)
    private lateinit var testObject: BaseViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    private val testScope = TestScope(testDispatcher)


    @Before
    fun setUp() {
        testObject = BaseViewModel(mockUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get character when when usescase provides success data it returns success state`() {

        val appType = AppType.SIMPSONS
        every {
            mockUseCase(appType)
        } returns flowOf(
            UIState.Success(
                listOf(mockk(), mockk())
            )
        )

//        var currentState: UIState<List<DomainChar>> = UIState.Loading

        val states = mutableListOf<UIState<List<DomainChar>>>()

        val job = testScope.launch {
            testObject.characters.collect {
                states.add((it))
            }
        }

//        val state = currentState as UIState.Success
//        testObject.getCharacters(appType)
//        assert(state.data.isEmpty())
//        assert(state is UIState.Success)

        testObject.getCharacters(appType)
        assert(states[0] is UIState.Loading)
        assert(states[1] is UIState.Success)
        val it = states[1] as UIState.Success<List<DomainChar>>
        assertEquals(2, it.data.size)
        job.cancel()

    }

    @Test
    fun `update  current match and new selected item`() {

        var dChar: DomainChar? = null
        val mockChar = mockk<DomainChar>(relaxed = true) {
            every { description } returns "some"
        }

        testObject.currentCharacter.observeForever {
            dChar = it
        }

        testObject.updateCurrentMatch(mockChar)
        assertNotNull(dChar)
        assertEquals("some", dChar?.description)
    }
}