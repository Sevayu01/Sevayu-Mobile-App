package com.example.sevayu.ui.Main.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sevayu.R
import com.example.sevayu.models.Blog
import com.example.sevayu.models.Department


class DepartmentAdapter(private val mList: List<Department>,val rainbow:Array<String>): RecyclerView.Adapter<DepartmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_department, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        holder.department.text = ItemsViewModel.department
        holder.itemView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(rainbow[position % rainbow.size])) )

        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_hospitalFragment_to_appointmentFinalFragment)
        }


//        when(holder.department.text){
//             "General" ->{
////                holder.itemView.background=ContextCompat.getDrawable(context,R.drawable.general)
//                 holder.itemView.setBackgroundResource(R.drawable.general)
//
//            }
//            "Cardiology"->{
////                holder.itemView.background=ContextCompat.getDrawable(context,R.drawable.cardio)
//                holder.itemView.setBackgroundResource(R.drawable.cardio)
//
//            }
//            "Orthopedics"->{
//               // holder.itemView.background=ContextCompat.getDrawable(context,R.drawable.orthopedics)
//                holder.itemView.setBackgroundResource(R.drawable.orthopedics)
//            }
//
//        }
//        holder.itemView.layoutParams.height=ViewGroup.LayoutParams.WRAP_CONTENT

    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val department: TextView = itemView.findViewById(R.id.tv_rvDepart)
    }

}