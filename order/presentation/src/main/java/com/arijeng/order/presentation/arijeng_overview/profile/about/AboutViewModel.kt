package com.arijeng.order.presentation.arijeng_overview.profile.about

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


/**
 * Created by {Bennette Molepo} on {2024/09/18}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class AboutViewModel(

) : ViewModel(){

    var state by mutableStateOf(AboutState())
        private set

}