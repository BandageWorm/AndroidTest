package com.kurt.activity

import android.content.Intent
import android.os.Bundle
import com.kurt.R

class MainActivity: BaseKotlinActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, PosterActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}