package com.example.sevayu.ui.Main

import android.annotation.SuppressLint
import android.util.Log
import android.util.Log.e
import androidx.constraintlayout.widget.StateSet.TAG
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevayu.models.game
import com.example.sevayu.models.hospital
import com.example.sevayu.repository.Resource
import com.example.sevayu.repository.hospitalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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


    private val performHospitalDataFetch = MutableLiveData<Resource<hospital>>()
    val performHospitalDataFetchStatus : MutableLiveData<Resource<hospital>>
    get() = performHospitalDataFetch

    @SuppressLint("LongLogTag")
    fun getHospitalData(id : String){
        viewModelScope.launch {
            try {

                performHospitalDataFetchStatus.value = Resource.loading()
                val response = hosRepo.getHospitalData(id)

                if (response.isSuccessful){
                    Log.d(TAG,"Hospital Data fetch call send ${response.body().toString()}")
                    if (response.body().toString().isNotEmpty()){

                        performHospitalDataFetch.value = Resource.success(response.body())
                    }else{
                        performHospitalDataFetch.value = Resource.empty()
                    }
                }else{
                    Log.d(TAG,"Hospital Data chud gya ${response.toString()}")
                }
            }catch (e : Exception){
                Log.e("Hospital data Mainview model" , e.toString())
                performHospitalDataFetch.value = Resource.error(e)
            }
        }
    }

}