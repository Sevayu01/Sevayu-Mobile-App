package com.example.sevayu.ui.Authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.sevayu.R
import com.example.sevayu.databinding.ActivityAuthBinding
import com.example.sevayu.ui.Authentication.authFragments.LoginFragment

class AuthActivity : AppCompatActivity() {
    var binding : ActivityAuthBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding?.root)

    }
}