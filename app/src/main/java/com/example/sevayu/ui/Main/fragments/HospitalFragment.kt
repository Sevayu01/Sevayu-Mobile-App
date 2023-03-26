package com.example.sevayu.ui.Main.fragments

import android.os.Bundle
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sevayu.R
import com.example.sevayu.models.Department
import com.example.sevayu.ui.Main.adapters.DepartmentAdapter
import com.example.sevayu.ui.Main.adapters.TestsAdapter


class HospitalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hospital, container, false)

        setupTestRv(view)
        setupDepartRv(view)
        return view

    }

    private fun setupDepartRv(view: View) {
        view.findViewById<RecyclerView>(R.id.rv_DepartMain)?.let {rv->
            var colors = arrayOf("#F06292","#81C784","#4DD0E1","#FFB74D")
            var dummyList = listOf<Department>(Department("Cardiology"),Department("Orthopedics"),Department("General"))
            rv.adapter= DepartmentAdapter(dummyList,colors)
            e("le","le")
            rv.layoutManager= GridLayoutManager(activity,2)
        }
    }

    private fun setupTestRv(view: View) {

        view.findViewById<RecyclerView>(R.id.rv_tests)?.let {rv->
            var dummyList = listOf<String>("Urine test","X-rays","CT scans","MRI scans","Endoscopy","Biopsy","Covid")
            rv.adapter= TestsAdapter(dummyList)
            rv.layoutManager= GridLayoutManager(activity,3)
        }

    }

}