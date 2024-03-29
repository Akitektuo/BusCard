package com.akitektuo.buscard

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
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

        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1000
        textConfirm.startAnimation(fadeIn)

        Handler().postDelayed({
            finish()
        }, 1500)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onBackPressed() {}
}
