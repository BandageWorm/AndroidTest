package com.qiming.kurtapp.ui

import android.os.Bundle
import android.util.Log
import com.qiming.kurtapp.R
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.lang.Exception

/**
 * Created by kurtg on 18/4/19.
 */
class KotlinActivity : CouroutineActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        Flowable.just(true)
        Flowable.create({ emmiter: FlowableEmitter<String?> ->
            emmiter.onNext("emit1")
            Thread.sleep(2000)
            emmiter.onNext("emit2")
            Thread.sleep(2000)
            emmiter.onNext("emit3")
            Thread.sleep(2000)
            emmiter.onNext("emit4")
            Thread.sleep(2000)
            emmiter.onComplete()
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()
        ).subscribe({ result ->
            Log.i("TAG", "next -->$result")
        }, { error ->
            Log.i("TAG", "error -->${error.message}")
        }, {
            Log.i("TAG", "complete -->")
        })

//        launch {
//            Thread.sleep(6000)
//            launch(UI) {
//                tv_a.text = "corotinA"
//            }
//        }

        load {
            Thread.sleep(2000)
            return@load "coroutineA"
        } then {
            tv_a.text = it
        }
    }

}

public fun main(args: Array<String>) {
    var str: String? = null
    try {
        str = Integer.valueOf("2c2").toString()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    print(str ?: "null")
}