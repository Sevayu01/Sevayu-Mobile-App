package com.example.sevayu.ui.Main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sevayu.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    var binding : FragmentUserBinding ?= null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(layoutInflater)
        return binding?.root
    }

}