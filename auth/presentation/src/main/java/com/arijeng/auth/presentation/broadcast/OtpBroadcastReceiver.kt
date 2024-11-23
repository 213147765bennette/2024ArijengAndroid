package com.arijeng.auth.presentation.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

/**
 * Created by {Bennette Molepo} on {2024/10/31}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

open class OtpBroadcastReceiver() : BroadcastReceiver() {
    private var onOtpReceived: ((String) -> Unit)? = null

    constructor(onOtpReceived: (String) -> Unit) : this() {
        this.onOtpReceived = onOtpReceived
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("OtpScreen", "The broad caster is triggered")
            if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
                val extras = intent.extras
                val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

                when (status.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        Log.e("OtpScreen", "SUCCESS CALL")
                        // SMS was retrieved successfully
                        val message = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as? String
                        message?.let {
                            val otp = extractOtpFromMessage(it)
                            onOtpReceived?.invoke(otp)
                            Log.d("OtpScreen","update viewmodel with otp: $otp")
                        }
                    }
                    CommonStatusCodes.TIMEOUT -> {
                        // Timed out waiting for SMS
                        Log.e("OtpScreen", "SMS retrieval timed out.")
                    }

                    else ->{
                        Log.e("OtpScreen", "Failed to retrieve OTP inside the OtpBroadcastReceiver.")
                        Toast.makeText(context, "Failed to retrieve OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun extractOtpFromMessage(message: String): String {
        // Assuming OTP is a 4-digit code in the message
        val smsCode = message.let { "[0-9]{4}".toRegex().find(it) }
        val otpRegex = Regex("\\d{4}")
       // return otpRegex.find(message)?.value ?: ""
        return smsCode.toString()
    }
}
