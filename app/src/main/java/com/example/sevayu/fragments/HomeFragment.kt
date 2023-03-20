package com.example.sevayu.fragments

import AppointmentAdapter
import android.os.Bundle
import android.util.Log
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sevayu.R
import com.example.sevayu.models.Appointments


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<RecyclerView>(R.id.rv_home)?.let { rv ->

            var colors = arrayOf("#F8BBD0","#C8E6C9","#B2EBF2","#FFE0B2")


            var dummyList= listOf(Appointments("AIIMS","Cancer","3:30-6:30","14 March,2023")
                ,Appointments("PMCH","Gyno","12:30-6:30","16 March,2023")
                ,Appointments("Apollo","Ortho","4:00-7:30","11 March,2023")
                ,Appointments("Max","Pedia","2:00-7:30","1 March,2023"))


            rv.layoutManager=LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            rv.adapter=AppointmentAdapter(dummyList,colors)
        }

        return view
    }




//    private fun setupRV() {
//        var dummyList= listOf(Appointments("aiims","cancer","3:30-6:30","14 March,2023")
//            ,Appointments("pmch","gyno","12:30-6:30","16 March,2023")
//            ,Appointments("Apollo","Ortho","4:00-7:30","11 March,2023"))
//
//        Log.e("here", dummyList.toString())
//        requireActivity().findViewById<RecyclerView>(R.id.rv_home)?.adapter=AppointmentAdapter(dummyList )
//        requireActivity().findViewById<RecyclerView>(R.id.rv_home)?.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
//    }


}