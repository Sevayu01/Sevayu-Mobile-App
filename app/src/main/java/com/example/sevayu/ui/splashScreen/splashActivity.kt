package com.example.sevayu.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.sevayu.databinding.ActivitySplashAcctivityBinding
import com.example.sevayu.ui.otp.otpActivity


class splashActivity : AppCompatActivity() {

    var binding : ActivitySplashAcctivityBinding ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashAcctivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        Handler().postDelayed({

            startActivity(Intent(this,otpActivity::class.java))
            finish()
        },2000)

    }
}