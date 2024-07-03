package com.arijeng.core.presentation.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


/**
 * Created by {Bennette Molepo} on {2024/05/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
sealed interface UiText {
    data class DynamicString(val value: String): UiText
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ): UiText

    @Composable
    fun asString(): String{
        return when(this){
            is DynamicString -> value
            is StringResource -> stringResource(id = id, *args)
        }
    }

    fun asString(context: Context): String{
        return when(this){
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}