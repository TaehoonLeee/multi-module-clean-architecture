package com.example.testpractice

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider

inline fun <reified T: Fragment> launchFragmentInHiltContainer(
	fragmentArgs: Bundle? = null,
	@StyleRes themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
	fragmentFactory: FragmentFactory? = null,
	crossinline action: T.() -> Unit = { }
) {
	val mainActivityIntent = Intent.makeMainActivity(
		ComponentName(
			ApplicationProvider.getApplicationContext(),
			HiltTestActivity::class.java,
		)
	).putExtra("androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity"
			+ ".THEME_EXTRAS_BUNDLE_KEY", themeResId)

	ActivityScenario.launch<HiltTestActivity>(mainActivityIntent).onActivity { activity ->
		fragmentFactory?.let {
			activity.supportFragmentManager.fragmentFactory = it
		}
		val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
			checkNotNull(T::class.java.classLoader),
			T::class.java.name
		)

		fragment.arguments = fragmentArgs
		activity.supportFragmentManager.beginTransaction()
			.add(android.R.id.content, fragment, "")
			.commitNow()

		(fragment as T).action()
	}
}