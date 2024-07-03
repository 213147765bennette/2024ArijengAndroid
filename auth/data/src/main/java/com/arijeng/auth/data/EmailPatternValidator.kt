package com.arijeng.auth.data

import android.util.Patterns
import com.arijeng.auth.domain.PatternValidator


/**
 * Created by {Bennette Molepo} on {2024/05/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
object EmailPatternValidator: PatternValidator {
    override fun matches(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}