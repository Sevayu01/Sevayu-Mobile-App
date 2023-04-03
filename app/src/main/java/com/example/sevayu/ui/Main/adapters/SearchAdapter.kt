package com.example.sevayu.ui.Main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sevayu.databinding.SearchItemLayoutBinding
import com.example.sevayu.models.game

class SearchAdapter(private var searchData: ArrayList<game>) :
    ListAdapter<game, SearchAdapter.SearchViewHolder>(DiffUtils()) {

    class SearchViewHolder(binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var title = binding.searchResultTitle
        var desc = binding.searchResultDesc
    }

    class DiffUtils : DiffUtil.ItemCallback<game>(){
        override fun areItemsTheSame(oldItem: game, newItem: game): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: game, newItem: game): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            SearchItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val model = getItem(position)

        holder.title.text = model.title
        holder.desc.text = model.plot.subSequence(0,60)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}