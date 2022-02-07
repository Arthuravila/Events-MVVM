package com.app.desafiosicredi.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.model.events.EventsItemResponse
import com.app.desafiosicredi.data.model.events.EventsResponse
import com.app.desafiosicredi.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.ui.events.EventsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class EventsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var mockedEventsRepository : EventsRepositoryImpl

    private lateinit var viewModel: EventsViewModel

    private val eventItem = EventsItemResponse(
        1602296327,
        "Evento",
        "1",
        "image.png",
        20.80,
        52.25,
        listOf(),
        30.5,
        "Teste"
    )

    private val eventsExpected: EventsResponse = EventsResponse()

    @Before
    fun setUp() {
        eventsExpected.add(eventItem)
        MockitoAnnotations.initMocks(this)
        viewModel = EventsViewModel(mockedEventsRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when get events success should return success`() = runBlockingTest {
        //Given
        `when`(mockedEventsRepository.getEvents()).thenReturn(getEventsSuccess())

        //When
        viewModel.getEvents()

        //Then
        assertNotNull(viewModel.events.value)
    }

    @Test
    fun `when get events error should return error`() = runBlockingTest {
        //Given
        `when`(mockedEventsRepository.getEvents()).thenReturn(getMockError())

        //When
        viewModel.getEvents()

        //Then
        assertTrue(viewModel.errorState.value?.errorVisibility == true)
    }

    private fun getEventsSuccess() = Result.Success(eventsExpected)
    private fun getMockError() = Result.Error("Algo deu errado")
}
