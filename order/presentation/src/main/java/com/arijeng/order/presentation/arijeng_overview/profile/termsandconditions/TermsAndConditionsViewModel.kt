package com.arijeng.order.presentation.arijeng_overview.profile.termsandconditions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


/**
 * Created by {Bennette Molepo} on {2024/09/18}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class TermsAndConditionsViewModel(

) : ViewModel(){

    var state by mutableStateOf(TermsAndConditionsState())
        private set

}