@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.arijeng.core.presentation.designsystem.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arijeng.core.presentation.designsystem.ArijengTheme
import com.arijeng.core.presentation.designsystem.CheckIcon
import com.arijeng.core.presentation.designsystem.EmailIcon
import com.arijeng.core.presentation.designsystem.SearchIcon


/**
 * Created by {Bennette Molepo} on {2024/05/14}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

@Composable
fun ArijengSearchTextField(
    state: TextFieldState,
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    hint: String,
    title: String?,
    modifier: Modifier = Modifier,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    additionalInfo: String? = null
) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
    ) {
        BasicTextField2(
            state = state,
            textStyle = LocalTextStyle.current.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isFocused) {
                        MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.05f
                        )
                    } else {
                        MaterialTheme.colorScheme.background
                    }
                )
                .border(
                    width = 1.dp,
                    color = if (isFocused) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            decorator = { innerBox ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(startIcon != null) {
                        Icon(
                            imageVector = startIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if(state.text.isEmpty() && !isFocused) {
                            Text(
                                text = hint,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimary.copy(
                                    alpha = 0.4f
                                ),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth()


                            )
                        }
                        innerBox()
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun ArijengSearchTextFieldPreview() {
    ArijengTheme {
        ArijengSearchTextField(
            state = rememberTextFieldState(),
            startIcon = SearchIcon,
            endIcon = CheckIcon,
            hint = "Search your favourite meal",
            title = "Email",
            additionalInfo = "Must be a valid email",
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}