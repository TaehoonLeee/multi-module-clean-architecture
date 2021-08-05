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

    companion object {
        private val componentClass = AtomicReference<Class<out FragmentComponent>>()

        fun registerFragmentComponent(componentClass: Class<out FragmentComponent>) {
            this.componentClass.compareAndSet(null, componentClass)
        }

        fun unregisterFragmentComponent() {
            this.componentClass.set(null)
        }

        fun instantiateFragmentComponent(fragment: Fragment): FragmentComponent {
            val componentClass = requireNotNull(componentClass.get()) { "Component class has not been set" }
            val ctor = componentClass.declaredConstructors.find { ctor ->
                Fragment::class.java == ctor.parameterTypes.singleOrNull()
            }
            requireNotNull(ctor) { "${componentClass.simpleName} must have a single argument constructor accepting ${Fragment::class.java}" }
            ctor.isAccessible = true

            return ctor.newInstance(fragment) as FragmentComponent
        }
    }
}