package com.example.sevayu.ui.Main.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.TAG
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sevayu.databinding.FragmentSearchBinding
import com.example.sevayu.models.game
import com.example.sevayu.repository.Resource
import com.example.sevayu.ui.Main.MainViewModal
import com.example.sevayu.ui.Main.adapters.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    var binding: FragmentSearchBinding? = null
    lateinit var viewModal: MainViewModal
    var gameList: ArrayList<game> = ArrayList()
    lateinit var adapter : SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        viewModal = ViewModelProvider(this)[MainViewModal::class.java]

        var textChangeVar = 0
        setObservers()
        setUpSearchResultRecyclerView()

        binding?.searchString?.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context, "Submitted Text : ${query}", Toast.LENGTH_SHORT).show()
                if (query != null) {
                    Log.e("SF req send" , "SF")
                    viewModal.getHospitals(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                textChangeVar++
                if (textChangeVar == 3) {
                    Log.e("SF req send" , "SF")
                    if (newText != null) {
                        val query = binding?.searchString?.query
                        viewModal.getHospitals(query.toString())
                    }
                    textChangeVar = 0
                }

                return true
            }

        })

        return binding?.root
    }

    private fun setUpSearchResultRecyclerView() {
        adapter = SearchAdapter(gameList)
        binding?.searchResultRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.searchResultRv?.setHasFixedSize(true)

        binding?.searchResultRv?.adapter = adapter
    }

    @SuppressLint("LongLogTag", "RestrictedApi")
    private fun setObservers() {
        viewModal.performHospitalFetchStatus.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Log.e(TAG,"Loading")
                    }
                    Resource.Status.EMPTY -> {
                        binding?.emptyTextView?.visibility = View.VISIBLE
                        binding?.searchResultRv?.visibility = View.GONE
                        Log.e(TAG,"Empty")
                    }
                    Resource.Status.SUCCESS -> {
                        binding?.emptyTextView?.visibility = View.GONE
                        binding?.searchResultRv?.visibility = View.VISIBLE
                        it.data?.let { it1 -> gameList = it1}
                        Toast.makeText(requireContext(), "Result aa gaya ${gameList}",
                            Toast.LENGTH_SHORT)
                        Log.e("Search Fragment Result aa gaya" , gameList.toString())
                        binding?.searchResultRv?.smoothScrollBy(0,gameList.size - 1)
                        adapter.submitList(gameList)
                        Log.e(TAG,"Success")

                    }
                    Resource.Status.ERROR -> {
                        binding?.emptyTextView?.visibility = View.VISIBLE
                        binding?.searchResultRv?.visibility = View.GONE
                        Log.e(TAG,"Error")
                    }
                    else -> {}
                }
            }
        })
    }

}


