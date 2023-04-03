package com.example.sevayu.repository

import com.example.sevayu.di.SevayuAPI
import javax.inject.Inject

class AuthRepository @Inject constructor(private val sevayuAPI: SevayuAPI) {

    suspend fun UserLogin() = sevayuAPI
}