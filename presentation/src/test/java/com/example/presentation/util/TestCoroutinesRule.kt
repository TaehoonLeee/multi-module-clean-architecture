package com.example.presentation.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class TestCoroutinesRule(
	private val testScope: TestScope = TestScope(),
	private val testDispatcher: TestDispatcher = StandardTestDispatcher(testScope.testScheduler)
) : TestWatcher() {

	override fun starting(description: Description?) {
		super.starting(description)

		Dispatchers.setMain(testDispatcher)
	}

	override fun finished(description: Description?) {
		super.finished(description)

		Dispatchers.resetMain()
	}

	fun runTest(block: suspend TestScope.() -> Unit) = testScope.runTest(testBody = block)
}