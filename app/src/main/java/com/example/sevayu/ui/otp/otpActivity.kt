package com.example.sevayu.ui.otp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sevayu.R
import com.example.sevayu.databinding.ActivityOtpBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class otpActivity : AppCompatActivity() {

    var binding : ActivityOtpBinding ?= null
    var auth : FirebaseAuth ?= null
    lateinit var verificationCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var progressDialog: ProgressDialog
    var storedVerificationId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.DoneBtn?.setOnClickListener {
            otpSent()
        }
    }

    private fun otpSent() {
        var phoneNo = binding?.etNumber?.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNo, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            verificationCallBack) // OnVerificationStateChangedCallbacks


        verificationCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Verification Successful!", Toast.LENGTH_LONG)
                    .show()
                binding?.etNumber?.setText(p0.smsCode)
                signInWithPhoneAuthCredential(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                progressDialog.dismiss()
                p0.printStackTrace()
                Toast.makeText(applicationContext, "Verification failed!", Toast.LENGTH_LONG).show()
                binding?.etNumber?.setText("")
//                binding.textResendOtp.isEnabled = true
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                progressDialog.dismiss()
                    storedVerificationId = verificationId
//                resendToken = token
//                binding.textResendOtp.isEnabled = false
//                countDownTimer.start()
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
//                binding.textResendOtp.isEnabled = true
                Toast.makeText(applicationContext, "Verification failed!", Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("LongLogTag")
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("signInWithCredential:success", credential.toString())

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("signInWithCredential:failure", task.exception.toString())
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
}