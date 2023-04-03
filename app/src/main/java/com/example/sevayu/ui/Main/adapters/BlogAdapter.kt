package com.example.sevayu.ui.Main.adapters

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sevayu.R
import com.example.sevayu.models.Blog


class BlogAdapter(private val mList: List<Blog>): RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        holder.title.text = ItemsViewModel.title
        holder.desc.text = ItemsViewModel.desc
        holder.author.text = ItemsViewModel.author
        holder.date.text = ItemsViewModel.date


        holder.desc.setMovementMethod(ScrollingMovementMethod())
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.rv_blogTitle)
        val desc: TextView = itemView.findViewById(R.id.rv_blogDesc)
        val author: TextView = itemView.findViewById(R.id.rv_blogAuthor)
        val date: TextView = itemView.findViewById(R.id.rv_BlogDate)
    }

}