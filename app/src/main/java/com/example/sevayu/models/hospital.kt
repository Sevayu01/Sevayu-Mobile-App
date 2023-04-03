package com.example.sevayu.models

import android.media.Image
import java.io.Serializable

data class hospital(
    val BloodBank: List<Any>,
    val Test: Test,
    val __v: Int,
    val _id: String,
    val appointments: List<Any>,
    val authenticated: Boolean,
    val city: String,
    val contact: String,
    val country: String,
    val createdAt: String,
    val doctors: List<Doctor>,
    val email: String,
    val images: List<Image>,
    val name: String,
    val password: String,
    val postalcode: String,
    val state: String,
    val street: String,
    val updatedAt: String
) : Serializable