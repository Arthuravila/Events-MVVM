package com.app.desafiosicredi.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.desafiosicredi.common.data.api.Result
import com.app.desafiosicredi.common.data.api.model.CheckinRequestBody
import com.app.desafiosicredi.common.data.api.model.CheckinResponse
import com.app.desafiosicredi.common.data.api.model.EventsItemResponse
import com.app.desafiosicredi.common.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.events.presentation.eventdetail.EventDetailViewModel
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
class EventDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var mockedEventsRepository: EventsRepositoryImpl

    private lateinit var viewModel: EventDetailViewModel

    private val checkinData = CheckinRequestBody(
        "Fulano Silva",
        "teste@teste.com",
        "1"
    )

    private val eventDetail = EventsItemResponse(
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

    private val eventId = "1"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = EventDetailViewModel(mockedEventsRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when get event by id success should return success`() = runBlockingTest {
        //Given
        `when`(mockedEventsRepository.getEventDetail(eventId)).thenReturn(getEventSuccess())

        //When
        viewModel.getEventDetail(eventId)

        //Then
        assertNotNull(viewModel.eventDetail.value)
    }

    @Test
    fun `when get event by id error should return error`() = runBlockingTest {
        //Given
        `when`(mockedEventsRepository.getEventDetail(eventId)).thenReturn(getMockError())

        //When
        viewModel.getEventDetail(eventId)

        //Then
        assertTrue(viewModel.errorState.value?.errorVisibility == true)
    }

    @Test
    fun `when make checkin confirmation success should return success`() = runBlockingTest {
        //Given
        `when`(mockedEventsRepository.makeCheckin(checkinData)).thenReturn(getCheckintSuccess())

        //When
        viewModel.makeCheckin(checkinData.name, checkinData.email, checkinData.eventId)

        //Then
        assertNotNull(viewModel.checkinResponse.value)
    }

    private fun getEventSuccess() = Result.Success(eventDetail)
    private fun getCheckintSuccess() = Result.Success(CheckinResponse("200"))
    private fun getMockError() = Result.Error("Algo deu errado")
}
