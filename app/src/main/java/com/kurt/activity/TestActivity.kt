package com.kurt.activity

import android.content.Intent
import android.os.Bundle
import com.kurt.R
import com.kurt.util.CouroutineFun.backTask
import com.kurt.util.CouroutineFun.postMain

/**
 * Created by kurtg on 18/4/19.
 */
class TestActivity : BaseKotlinActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        backTask {
            Thread.sleep(2000)
        } postMain {
            startActivity(Intent(this, PosterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}