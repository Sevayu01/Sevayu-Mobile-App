package com.example.sevayu.ui.splashScreen

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.transition.Explode
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.sevayu.databinding.ActivitySplashAcctivityBinding
import com.example.sevayu.ui.Main.MainActivity
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

//        window.setExitTransition(null)

        var logo = binding?.SevayuLogo
        Handler().postDelayed({

            var intent = Intent(this,MainActivity::class.java)

//            var options = ActivityOptions.makeSceneTransitionAnimation(this,logo,"Sevayu_logo")

//            startActivity(intent,options.toBundle())
            startActivity(intent)
            finish()
        },2000)

    }
}