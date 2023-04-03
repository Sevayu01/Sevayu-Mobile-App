package com.example.sevayu.ui.Main.fragments

import android.os.Bundle
import android.util.Log
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.StateSet.TAG
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sevayu.R
import com.example.sevayu.databinding.FragmentHospitalBinding
import com.example.sevayu.models.Department
import com.example.sevayu.repository.Resource
import com.example.sevayu.ui.Main.MainViewModal
import com.example.sevayu.ui.Main.adapters.DepartmentAdapter
import com.example.sevayu.ui.Main.adapters.TestsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HospitalFragment : Fragment() {
    var binding : FragmentHospitalBinding ?= null
    lateinit var viewModal: MainViewModal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHospitalBinding.inflate(layoutInflater)

        viewModal = ViewModelProvider(this)[MainViewModal::class.java]
        setObservers()
        Log.e(TAG,"send call")
        viewModal.getHospitalData("64240e18c8c8a6b8f2fca37b")


        setupTestRv()
        setupDepartRv()
        return binding?.root

    }

    private fun setObservers() {
        viewModal.performHospitalDataFetchStatus.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Log.e("hosFrag" , "Loading")
                    }
                    Resource.Status.EMPTY -> {
                        Log.e("hosFrag" , "empty")
                    }
                    Resource.Status.SUCCESS -> {
                        Log.e("hosFrag" , "Success")
                        val hospital = it.data
                        Toast.makeText(requireContext(),
                        hospital.toString(),Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.ERROR -> {
                        Log.e("hosFrag" , "Error")
                    }
                    else -> {}
                }
            }
        })
    }

    private fun setupDepartRv() {
        binding?.rvDepartMain.let {rv->
            var colors = arrayOf("#F06292","#81C784","#4DD0E1","#FFB74D")
            var dummyList = listOf<Department>(Department("Cardiology"),Department("Orthopedics"),Department("General"))
            rv?.adapter= DepartmentAdapter(dummyList,colors)
            e("le","le")
            rv?.layoutManager= GridLayoutManager(activity,2)
        }
    }

    private fun setupTestRv() {

        binding?.rvTests.let {rv->
            var dummyList = listOf<String>("Urine test","X-rays","CT scans","MRI scans","Endoscopy","Biopsy","Covid")
            rv?.adapter= TestsAdapter(dummyList)
            rv?.layoutManager= GridLayoutManager(activity,3)
        }

    }

}