package com.example.sevayu.ui.Authentication

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.sevayu.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepo : AuthRepository) : ViewModel() {

    fun validateCredentials(username : String , emailAddress : String, passsword : String) : Pair<Boolean,String>{
        var result = Pair(true,"")
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(passsword)){
            result = Pair(false , "Please provide the credentials")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            result = Pair(false, "Please provide valid email")
        }else if(passsword.length <= 5){
            result = Pair(false,"Password length is too short")
        }

        return result
    }
}