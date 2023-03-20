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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.sevayu.Adapters.BlogAdapter
import com.example.sevayu.R
import com.example.sevayu.models.Appointments
import com.example.sevayu.models.Blog


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        setupDeptRV(view)
        setupBlogRv(view)

        return view
    }




    private fun setupDeptRV(view: View) {
        view.findViewById<RecyclerView>(R.id.rv_home)?.let { rv ->

            var colors = arrayOf("#F06292","#81C784","#4DD0E1","#FFB74D")


            var dummyList= listOf(Appointments("AIIMS","Cancer","3:30-6:30","14 March,2023")
                ,Appointments("PMCH","Gyno","12:30-6:30","16 March,2023")
                ,Appointments("Apollo","Ortho","4:00-7:30","11 March,2023")
                ,Appointments("Max","Pedia","2:00-7:30","1 March,2023"))


            rv.layoutManager=LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            rv.adapter=AppointmentAdapter(dummyList,colors)
        }
    }

    private fun setupBlogRv(view: View){

        view.findViewById<RecyclerView>(R.id.rv_blogHome)?.let { rv ->
            var dummyList = listOf(
                Blog(
                    "Dr. Praneki",
                    "Why Non veg food very good",
                    "20 March 2023",
                    "coz it tastes good lmao and veg food is kinda mid"
                ),
                Blog(
                    "Dr Yogi",
                    "Yoga cures cancer",
                    "2 March 2023",
                    "Waking up every morning at 6:00AM, Suryanamaskar and a fresh cup of gomutra can cure cancer"
                ),
                Blog(
                    "WHO",
                    "Medical discovery in Africa",
                    "26 Nov 2008",
                    "Scientists discover the main cause of lower health index in central african countries and to everyone's surprise it's poverty."
                ),
                Blog(
                    "Dr Maro",
                    "Premature ejaculation can change your life",
                    "6 September 2023",
                    "will let me satisfy your soul mate"
                ),
                Blog(
                    "Dr. Praneki",
                    "Why Non veg food very good",
                    "20 March 2023",
                    "coz it tastes good lmao and veg food is kinda mid"
                ),
                Blog(
                    "Dr Yogi",
                    "Yoga cures cancer",
                    "2 March 2023",
                    "Waking up every morning at 6:00AM, Suryanamaskar and a fresh cup of gomutra can cure cancer"
                )
            )
            rv.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rv.adapter= BlogAdapter(dummyList)


        }
    }


}