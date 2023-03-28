package com.example.sevayu.ui.Main.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sevayu.databinding.FragmentSearchBinding
import com.example.sevayu.di.Constants
import com.example.sevayu.models.game
import com.example.sevayu.models.gameList
import com.example.sevayu.repository.Resource
import com.example.sevayu.ui.Main.MainViewModal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    var binding: FragmentSearchBinding? = null
    lateinit var viewModal: MainViewModal
    lateinit var gameList: ArrayList<game>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        viewModal = ViewModelProvider(this)[MainViewModal::class.java]

        var textChangeVar = 0
        setObservers()

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
                if (textChangeVar == 5) {
                    Toast.makeText(context, "changed Text : ${newText}", Toast.LENGTH_SHORT).show()
                    textChangeVar = 0
                }

                return true
            }

        })

        return binding?.root
    }

    @SuppressLint("LongLogTag")
    private fun setObservers() {
        viewModal.performHospitalFetchStatus.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                    }
                    Resource.Status.EMPTY -> {
                        binding?.emptyTextView?.visibility = View.VISIBLE
                        binding?.searchResultRv?.visibility = View.GONE
                    }
                    Resource.Status.SUCCESS -> {
                        binding?.emptyTextView?.visibility = View.GONE
                        binding?.searchResultRv?.visibility = View.VISIBLE
                        it.data?.let { it1 -> gameList = it1}
                        Toast.makeText(requireContext(), "Result aa gaya ${gameList}",
                            Toast.LENGTH_SHORT)
                        Log.e("Search Fragment Result aa gaya" , gameList.toString())
                    }
                    Resource.Status.ERROR -> {
                        binding?.emptyTextView?.visibility = View.VISIBLE
                        binding?.searchResultRv?.visibility = View.GONE
                    }
                    else -> {}
                }
            }
        })
    }

}


