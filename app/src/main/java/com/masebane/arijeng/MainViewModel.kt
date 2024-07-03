package com.masebane.arijeng

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arijeng.core.domain.SessionStorage
import kotlinx.coroutines.launch


/**
 * Created by {Bennette Molepo} on {2024/05/22}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class MainViewModel(
    private val sessionStorage: SessionStorage
): ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(isCheckingAuth = true)
            state = state.copy(
                isLoggedIn = sessionStorage.get() != null
            )
            state = state.copy(isCheckingAuth = false)
        }
    }
}