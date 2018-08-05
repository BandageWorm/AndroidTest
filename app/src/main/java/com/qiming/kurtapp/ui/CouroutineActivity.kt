package com.qiming.kurtapp.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.HandlerContext
import kotlinx.coroutines.experimental.android.UI

/**
 * Created by kurtg on 18/8/5.
 */
abstract class CouroutineActivity : AppCompatActivity() {

    companion object {
        val background = newFixedThreadPoolContext(8, "background")
    }

    fun <T> LifecycleOwner.load(loader: () -> T): Deferred<T> {
        val deferred = async(context = background, start = CoroutineStart.DEFAULT) {
            loader()
        }
        lifecycle.addObserver(CoroutineLifecycleListener(deferred))
        return deferred
    }

    infix fun <T> Deferred<T>.then(block: (T) -> Unit): Job {
        return launch(context = UI) {
            block(this@then.await())
        }
    }

    class CoroutineLifecycleListener(val deferred: Deferred<*>) : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun cancelCoroutine() {
            if (!deferred.isCancelled) {
                deferred.cancel()
            }
        }
    }
}