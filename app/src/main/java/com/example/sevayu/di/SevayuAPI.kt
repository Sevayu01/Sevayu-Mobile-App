package com.example.sevayu.di

import androidx.lifecycle.LiveData
import com.example.sevayu.models.game
import com.example.sevayu.models.gameList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface SevayuAPI {

    //Data List
    @GET("product/search/{query}")
    suspend fun getDataList(@Path("query") query:String) : Response<gameList>
}