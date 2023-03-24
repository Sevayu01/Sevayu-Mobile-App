package com.example.sevayu.ui.otp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.sevayu.databinding.ActivityOtpBinding
import com.example.sevayu.ui.Main.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class otpActivity : AppCompatActivity() {

    var binding : ActivityOtpBinding ?= null
    var auth : FirebaseAuth ?= null
    var storedVerificationId : String ?= null
    var resendToken : PhoneAuthProvider.ForceResendingToken ?= null
    var progressDialog : ProgressDialog ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.setEnterTransition(null)

        val phoneNo = binding?.etNumber?.text
        progressDialog?.setMessage("OTP Sent")
        auth = FirebaseAuth.getInstance()

        otpEditTextInput()

        binding?.DoneBtn?.setOnClickListener {
            if (binding?.DoneBtn?.text == "Send OTP"){
                if(phoneNo?.length == 10){
                    otpSent(phoneNo.toString())
                    Log.e("Phone No Succ" , phoneNo.toString())
                    progressDialog?.show()
                }else{
                    Toast.makeText(applicationContext,
                        "Enter a valid phone Number $phoneNo",Toast.LENGTH_SHORT).show()
                    Log.e("Phone No" , phoneNo.toString())
                }
            }else{
                if(binding?.otpInput1?.text?.toString()?.trim()?.isEmpty() == true ||
                binding?.otpInput2?.text?.toString()?.trim()?.isEmpty() == true ||
                binding?.otpInput3?.text?.toString()?.trim()?.isEmpty() == true ||
                binding?.otpInput4?.text?.toString()?.trim()?.isEmpty() == true ||
                binding?.otpInput5?.text?.toString()?.trim()?.isEmpty() == true ||
                binding?.otpInput6?.text?.toString()?.trim()?.isEmpty() == true
                        ){
                    Toast.makeText(applicationContext,"OTP is not Valid",
                    Toast.LENGTH_SHORT).show()
                }else{
                    if (storedVerificationId != null){
                        var code = binding?.otpInput1?.text?.toString()?.trim() +
                                binding?.otpInput2?.text?.toString()?.trim() +
                                binding?.otpInput3?.text?.toString()?.trim() +
                                binding?.otpInput4?.text?.toString()?.trim() +
                                binding?.otpInput5?.text?.toString()?.trim() +
                                binding?.otpInput6?.text?.toString()?.trim()

                        var credentials = PhoneAuthProvider.getCredential(
                            storedVerificationId!!,code
                        )
                        signInWithPhoneAuthCredential(credentials)

                    }
                }
            }
        }
    }

    private fun otpEditTextInput() {
        binding?.otpInput1?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding?.otpInput2?.requestFocus()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding?.otpInput2?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding?.otpInput3?.requestFocus()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding?.otpInput3?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding?.otpInput4?.requestFocus()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding?.otpInput4?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding?.otpInput5?.requestFocus()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding?.otpInput5?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding?.otpInput6?.requestFocus()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun otpSent(phoneNo : String) {
         var mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
             override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                 Log.d("OTP Activity", "onVerificationCompleted:$credential")
                 signInWithPhoneAuthCredential(credential)
             }

             override fun onVerificationFailed(e: FirebaseException) {
                 Log.w("OTP Activity", "onVerificationFailed", e)
                 progressDialog?.dismiss()
                 if (e is FirebaseAuthInvalidCredentialsException) {
                     Toast.makeText(applicationContext,"Wrong OTP,Try Again",
                         Toast.LENGTH_SHORT).show()
                 } else if (e is FirebaseTooManyRequestsException) {
                     Toast.makeText(applicationContext,"Too Many requests",
                         Toast.LENGTH_SHORT).show()
                 }
             }

             override fun onCodeSent(
                 verificationId: String,
                 token: PhoneAuthProvider.ForceResendingToken
             ) {
                 Log.d("OTP Activity", "onCodeSent:$verificationId")
                 binding?.etNumber?.visibility = View.GONE
                 binding?.otpLayout?.visibility = View.VISIBLE
                 binding?.DoneBtn?.text = "Verify "
                 progressDialog?.dismiss()
                 storedVerificationId = verificationId
                 resendToken = token
             }

         }

        val options = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber("+91$phoneNo")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                    Log.d("OtpActivity", "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    Log.w("OtpActivity", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(applicationContext,
                        "Invalid Verification Code",Toast.LENGTH_SHORT).show()
                    }
                    binding?.DoneBtn?.text = "Send OTP"
                    binding?.etNumber?.visibility = View.VISIBLE
                    binding?.otpLayout?.visibility = View.GONE
                }
            }
    }

    //Code for Checking Internet connection


}