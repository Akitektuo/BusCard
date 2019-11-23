package com.akitektuo.buscard

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_success.*

class SuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        when (val doneDrawable = imageDone.drawable) {
            is AnimatedVectorDrawable -> doneDrawable.start()
            is AnimatedVectorDrawableCompat -> doneDrawable.start()
        }

        Handler().postDelayed({
            finish()
        }, 1000)
    }

    override fun onBackPressed() {}
}
