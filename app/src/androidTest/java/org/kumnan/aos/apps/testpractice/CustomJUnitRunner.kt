package org.kumnan.aos.apps.testpractice

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.testing.HiltTestApplication
import java.util.concurrent.atomic.AtomicReference

class CustomJUnitRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}