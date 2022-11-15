package com.example.testpractice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.features.gallery.GalleryFragment
import com.example.features.gallery.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class GalleryFragmentTest {

	@get:Rule(order = 0)
	val hiltRule = HiltAndroidRule(this)

	@get:Rule(order = 1)
	val instantTaskExecutorRule = InstantTaskExecutorRule()

	@Before
	fun launchScreen() {
		launchFragmentInHiltContainer<GalleryFragment>()
	}

	@Test
	fun isRecyclerViewVisible_OnStart() {
		Espresso.onView(ViewMatchers.withId(R.id.rvPhoto))
			.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

		Espresso.onView(ViewMatchers.withId(R.id.rvPhoto))
			.check { view, _ ->
				assertThat((view as RecyclerView).adapter?.itemCount, `is`(1))
			}
	}

	@Test
	fun isItemContentsAppropriate() {
		Espresso.onView(ViewMatchers.withId(R.id.text_view_user_name))
			.check(ViewAssertions.matches(withText("first_user")))
	}
}