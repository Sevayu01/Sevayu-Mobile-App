package com.example.sevayu.ui.Main.adapters

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sevayu.R
import com.example.sevayu.models.Blog


class TestsAdapter(private val mList: List<String>): RecyclerView.Adapter<TestsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tests, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        holder.tests.text = ItemsViewModel

    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tests: TextView = itemView.findViewById(R.id.tv_rvTests)
    }

}