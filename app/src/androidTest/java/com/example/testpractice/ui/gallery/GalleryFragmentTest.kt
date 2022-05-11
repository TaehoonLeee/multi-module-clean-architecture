package com.example.testpractice.ui.gallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.presentation.R
import com.example.presentation.ui.gallery.GalleryFragment
import com.example.testpractice.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
	}
}