@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.arijeng.auth.presentation.otp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.arijeng.auth.presentation.R
import com.arijeng.auth.presentation.broadcast.OtpBroadcastReceiver
import com.arijeng.core.presentation.designsystem.ArijengTheme
import com.arijeng.core.presentation.designsystem.components.ArijengActionButton
import com.arijeng.core.presentation.designsystem.components.GradientBackground
import com.arijeng.core.presentation.ui.ObserveAsEvents
import com.google.android.gms.auth.api.phone.SmsRetriever
import org.koin.androidx.compose.koinViewModel

/**
 * Created by {Bennette Molepo} on {2024/07/08}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Composable
fun OTPScreenRoot(
    onSuccessfulOTPConfirmation: () -> Unit,
    viewModel: OTPViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is OTPEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(context, event.error.asString(context), Toast.LENGTH_LONG).show()
            }

            OTPEvent.OTPConfirmationSuccess -> {
                keyboardController?.hide()
                Toast.makeText(context, R.string.otp_confirmation_successful, Toast.LENGTH_LONG).show()
                onSuccessfulOTPConfirmation()
            }
        }
    }

    RegisterOTPBroadCaster(viewModel)
}
@Composable
private fun RegisterOTPBroadCaster(viewModel: OTPViewModel){
    val context = LocalContext.current
    var otpValue by remember { mutableStateOf("") }

    // Set up the BroadcastReceiver for SMS Retriever
    val otpReceiver = remember {
        OtpBroadcastReceiver { receivedOtp ->
            otpValue = receivedOtp
            viewModel.updateOtp(receivedOtp)  // Update ViewModel when OTP is received
            Log.d("OtpScreen","update viewmodel with otp: $receivedOtp")
        }
    }
    /*DisposableEffect(Unit) {
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        context.registerReceiver(otpReceiver, intentFilter)

        val client = SmsRetriever.getClient(context)
        client.startSmsRetriever()
            .addOnSuccessListener {
                Log.d("OtpScreen", "SMS Retriever started successfully.")
            }
            .addOnFailureListener {
                Log.e("OtpScreen", "Failed to start SMS Retriever.")
            }

        onDispose {
            Log.d("OtpScreen", "unregister broadcast Receiver.")
            context.unregisterReceiver(otpReceiver)
        }
    }*/


    OTPScreen(
        otpValue = otpValue,
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}



@Composable
private fun OTPScreen(
    otpValue: String,
    state: OTPState,
    onAction: (OTPAction) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 14.dp)
        ) {
            Text(
                text = stringResource(id = R.string.otp_confirmation_title),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(id = R.string.otp_confirmation_message),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(50.dp))

            OtpInputScreen(otpValue,4, onOtpComplete = {

            })

            Spacer(modifier = Modifier.height(38.dp))
            ArijengActionButton(
                text = stringResource(id = R.string.confirm),
                isLoading = state.isOTPConfirming,
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(OTPAction.OnConfirmOTPClick)
                }
            )
        }
    }
}
@Composable
fun OtpInputScreen(otpCode: String,otpLength: Int = 4, onOtpComplete: (String) -> Unit) {
    var otpValue by remember { mutableStateOf("") }
    otpValue = otpCode
    Log.d("OtpScreen","The recived otp code: $otpCode")
    var isFocused by remember { mutableStateOf(false) }

    if (otpCode.length == otpLength) {
        onOtpComplete(otpCode)
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        for (i in 0 until otpLength) {
            BasicTextField(
                value = otpCode.getOrNull(i)?.toString() ?: "",
                onValueChange = { newValue ->
                    if (newValue.length <= otpLength && newValue.all { it.isDigit() }) {
                        otpValue = newValue
                        //isFocused = otpCode.length ==i
                    }
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                singleLine = true,
                modifier = Modifier
                    .size(48.dp)
                    .border(
                        2.dp, when {
                            isFocused -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.secondary
                        }, RoundedCornerShape(8.dp)
                    )
                    .padding(start = 15.dp, end = 5.dp),
                visualTransformation = VisualTransformation.None,
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(Color.Transparent)
                    ) {
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

/*@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    index: Int,
    otpText: String,
    otpCount: Int = 4,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    BasicTextField2(
        state = state,
        modifier = modifier
            .height(50.dp)
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorator = {
            Row(
                modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center
            ) {
               repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

        }
    )
}*/

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .border(
                3.dp, when {
                    isFocused -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.secondary
                }, RoundedCornerShape(8.dp)
            )
            .padding(start = 2.dp, end = 2.dp, top = 15.dp),
        text = char,
        style = MaterialTheme.typography.titleSmall,
        color = if (isFocused) {
            MaterialTheme.colorScheme.secondary
        } else {
            MaterialTheme.colorScheme.primary
        },
        textAlign = TextAlign.Center
    )
}

//@Preview
@Composable
private fun OTPScreenPreview() {
    ArijengTheme {
        OTPScreen(
            otpValue = "3333",
            state = OTPState()
        ) {}
    }
}
@Preview
@Composable
private fun OtpInputScreenPreview() {
    ArijengTheme {
        OtpInputScreen(
            "3434",
            otpLength = 4,
            onOtpComplete = {}
        )
    }
}
