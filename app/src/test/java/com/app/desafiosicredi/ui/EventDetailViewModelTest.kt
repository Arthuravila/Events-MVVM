package com.app.desafiosicredi.ui

import android.os.Build
import com.app.desafiosicredi.data.events.repository.EventsRepository
import com.app.desafiosicredi.ui.eventdetail.EventDetailViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class EventDetailViewModelTest {
    private val mockedEventsRepository = mockk<EventsRepository>(relaxed = true)

    private val viewModel = EventDetailViewModel(mockedEventsRepository)

    @Test
    fun onGetEvents_mustShowLoading() {
        viewModel.progressBarVisibility.value?.let { assert(it).equals(true) }
    }

    @Test
    fun mustCallGetEvents(): Unit = runBlocking {
        viewModel.getEventDetail("1")
        coEvery { mockedEventsRepository.getEventDetail("1") }
    }
}