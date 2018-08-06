package com.kurt.util

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

object CouroutineFun {

    val background = newFixedThreadPoolContext(8, "background")

    public fun <T> LifecycleOwner.backTask(loader: () -> T): Deferred<T> {
        val deferred = async(context = background, start = CoroutineStart.DEFAULT) {
            loader()
        }
        lifecycle.addObserver(CoroutineLifecycleListener(deferred))
        return deferred
    }

    public infix fun <T> Deferred<T>.postMain(block: (T) -> Unit): Job {
        return launch(context = UI) {
            block(this@postMain.await())
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