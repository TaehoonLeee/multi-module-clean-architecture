package org.kumnan.aos.apps.testpractice.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> LiveData<T>.awaitValue(): T? {
    var data: T? = null
    val latch = CountDownLatch(1)

    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            removeObserver(this)
            latch.countDown()
        }
    }

    observeForever(observer)

    try {
        if (!latch.await(2, TimeUnit.SECONDS)) {
            throw TimeoutException("Live Data never gets the value.")
        }
    } finally {
        removeObserver(observer)
    }



    return data
}