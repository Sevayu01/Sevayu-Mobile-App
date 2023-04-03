package com.example.sevayu.models

import com.google.gson.annotations.SerializedName

data class gameList(
    @SerializedName("result")
    val result: ArrayList<game> = ArrayList()
)
