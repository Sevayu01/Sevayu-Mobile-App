package com.example.sevayu.ui.Main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevayu.models.game
import com.example.sevayu.models.gameList
import com.example.sevayu.repository.Resource
import com.example.sevayu.repository.hospitalRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainViewModal @Inject constructor(private val hosRepo: hospitalRepository) : ViewModel() {

    private val performHospitalFetch = MutableLiveData<Resource<ArrayList<game>>>()
    val performHospitalFetchStatus: MutableLiveData<Resource<ArrayList<game>>>
        get() = performHospitalFetch

    @SuppressLint("LongLogTag")
    fun getHospitals(query: String) {
        viewModelScope.launch {
            try {
                performHospitalFetchStatus.value = Resource.loading()
                Log.e("SFVM req send" , "SFVM")
                val response =hosRepo.searchHospital(query)
                if (response.isSuccessful){
                    if (response.body().toString().isNotEmpty()){
                        Log.e("Search View Modal : Search response" , response.toString())
                        performHospitalFetch.value = Resource.success(response.body()?.result)
                    }else{
                        performHospitalFetch.value = Resource.empty()
                    }
                }
            }catch (e : Exception){
                Log.e("Search View Modal : Search error" , e.toString())
                performHospitalFetch.value = Resource.error(e)
            }
        }
    }

}