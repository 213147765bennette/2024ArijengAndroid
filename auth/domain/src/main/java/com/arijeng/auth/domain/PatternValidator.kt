package com.arijeng.auth.domain


/**
 * Created by {Bennette Molepo} on {2024/05/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
interface PatternValidator {
    fun matches(value: String):Boolean
}