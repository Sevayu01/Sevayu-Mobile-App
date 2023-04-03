package com.example.sevayu.repository

import com.example.sevayu.di.SevayuAPI
import javax.inject.Inject

class hospitalRepository @Inject constructor(private val sevayuAPI: SevayuAPI) {

    suspend fun searchHospital(query : String) = sevayuAPI.getDataList(query)

    suspend fun getHospitalData(id : String) = sevayuAPI.getHospitalData(id)

}