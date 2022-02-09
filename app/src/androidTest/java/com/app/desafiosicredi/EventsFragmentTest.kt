package com.app.desafiosicredi

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.app.desafiosicredi.events.presentation.events.EventsFragment
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class EventsFragmentTest {

    @Test
    fun mustShowEventsRecyclerView() {
        launchFragmentInContainer<EventsFragment>(null, R.style.AppTheme)
        Espresso.onView(withId(R.id.events_recyclerview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onEventsItemClicked_mustNavigateToEventDetails() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        val onboardingScenario = launchFragmentInContainer<EventsFragment>(null, R.style.AppTheme)

        onboardingScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        navController.navigate(R.id.eventsFragment)

        Espresso.onView(withId(R.id.events_recyclerview)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.eventDetailFragment)
    }
}
