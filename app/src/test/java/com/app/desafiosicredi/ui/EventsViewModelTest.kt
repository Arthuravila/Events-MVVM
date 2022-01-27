package com.app.desafiosicredi.ui

import android.os.Build
import com.app.desafiosicredi.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.ui.events.EventsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class EventsViewModelTest {
    private val mockedEventsRepository = mockk<EventsRepositoryImpl>(relaxed = true)

    private val viewModel = EventsViewModel(mockedEventsRepository)

    @Test
    fun onGetEvents_mustShowLoading() {
        viewModel.progressBarVisibility.value?.let { assert(it).equals(true) }
    }

    @Test
    fun mustCallGetEvents(): Unit = runBlocking {
        viewModel.getEvents()
        coEvery { mockedEventsRepository.getEvents() }
    }
}
