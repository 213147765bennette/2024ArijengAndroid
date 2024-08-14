@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.arijeng.auth.presentation.otp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arijeng.auth.presentation.R
import com.arijeng.core.presentation.designsystem.ArijengTheme
import com.arijeng.core.presentation.designsystem.components.ArijengActionButton
import com.arijeng.core.presentation.designsystem.components.GradientBackground
import com.arijeng.core.presentation.ui.ObserveAsEvents
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

    OTPScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun OTPScreen(
    state: OTPState,
    onAction: (OTPAction) -> Unit
) {
    var otpValue by remember {
        mutableStateOf("")
    }
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

            val otpStates = listOf(state.otpFieldOne,state.otpFieldTwo,state.otpFieldThree,state.otpFieldFour)
            Row(
                modifier = Modifier
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                otpStates.forEachIndexed { otpIndex, textFieldState ->
                    OtpTextField(
                        otpText = otpValue,
                        state = textFieldState,
                        index = otpIndex,
                        onOtpTextChange = { value, _ ->
                            otpValue = value
                            Log.d("----otpchanges","otp value is changing: $otpValue")
                        }
                    )
                }
            }


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

   /* BasicTextField(
        modifier = modifier
            .height(50.dp)
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {
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
    )*/

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
              /*  CharView(
                    index = index,
                    text = otpText
                )
                Spacer(modifier = Modifier.width(8.dp))*/
            }

        }
    )
}

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
            .padding(2.dp, top = 10.dp),
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

@Preview
@Composable
private fun OTPScreenPreview() {
    ArijengTheme {
        OTPScreen(
            state = OTPState(),
            onAction = {}
        )
    }
}