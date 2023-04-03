package com.example.sevayu.ui.Authentication.authFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sevayu.R
import com.example.sevayu.databinding.FragmentRegisterBinding
import com.example.sevayu.ui.Authentication.AuthViewModel
import com.example.sevayu.ui.Main.MainViewModal

class RegisterFragment : Fragment() {

    var binding : FragmentRegisterBinding ?= null
    lateinit var authViewModel  :AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding?.goToLogin?.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding?.root
    }

    private fun validateUserInput() : Pair<Boolean, String> {
        val email = binding?.etEmail?.text.toString()
        val password = binding?.etpass?.text.toString()
        val username = binding?.etUsername?.text.toString()
        return authViewModel.validateCredentials(username,email,password)
    }
}