@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")

package com.arijeng.order.presentation.arijeng_overview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arijeng.core.domain.arijeng_overview.MenuOverViewRepository
import com.arijeng.core.domain.util.Result
import com.arijeng.core.domain.arijeng_overview.dto.ChipsDTO
import com.arijeng.core.domain.arijeng_overview.dto.IceCreamDTO
import com.arijeng.core.domain.arijeng_overview.dto.ItemDTO
import com.arijeng.core.domain.arijeng_overview.dto.KotaDTO
import com.arijeng.core.domain.arijeng_overview.dto.MenuDTO
import com.arijeng.core.domain.arijeng_overview.dto.SaladDTO
import com.arijeng.core.domain.arijeng_overview.dto.SandwichDTO
import com.arijeng.core.domain.arijeng_overview.dto.SoftDrinkDTO
import com.arijeng.core.domain.arijeng_overview.dto.toItemDTO
import com.arijeng.order.presentation.arijeng_overview.ui.SectionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn


/**
 * Created by {Bennette Molepo} on {2024/08/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class ArijengOverviewViewModel(
    menuOverViewRepository: MenuOverViewRepository
) : ViewModel() {

    var state by mutableStateOf(ArijengOverviewState())
        private set
    private val _menuDetails = MutableStateFlow(
        MenuDTO(
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )
    )

    private val kotaDetails: MutableState<List<KotaDTO>> = mutableStateOf(emptyList())
    private val chipsDetails: MutableState<List<ChipsDTO>> = mutableStateOf(emptyList())
    private val iceCreamDetails: MutableState<List<IceCreamDTO>> = mutableStateOf(emptyList())
    private val saladsDetails: MutableState<List<SaladDTO>> = mutableStateOf(emptyList())
    private val sandwichDetails: MutableState<List<SandwichDTO>> = mutableStateOf(emptyList())
    private val drinksDetails: MutableState<List<SoftDrinkDTO>> = mutableStateOf(emptyList())

   /* val menuDetails = _menuDetails
        .onStart {
            menuOverViewRepository.getHomeOverviewMenu().onEach {
                if (it is Result.Success) {
                    _menuDetails.value = it.data
                }
            }.launchIn(viewModelScope)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )*/
    init {
        menuOverViewRepository.getHomeOverviewMenu().onEach {
            if (it is Result.Success) {
                _menuDetails.value = it.data
            }
        }.launchIn(viewModelScope)
    }

    private fun kotaList(): List<ItemDTO> {
        _menuDetails.value.let {
            kotaDetails.value = it.kotaDTOS
        }
        return kotaDetails.value.map { it.toItemDTO() }
    }

    private fun chipsList(): List<ItemDTO> {
        _menuDetails.value.let {
            chipsDetails.value = it.chipsDTOS
        }
        return chipsDetails.value.map { it.toItemDTO() }
    }

    private fun iceCreamList(): List<ItemDTO> {
        _menuDetails.value.let {
            iceCreamDetails.value = it.iceCreamDTOS
        }
        return iceCreamDetails.value.map { it.toItemDTO() }
    }

    private fun saladsList(): List<ItemDTO> {
        _menuDetails.value.let {
            saladsDetails.value = it.saladDTOS
        }
        return saladsDetails.value.map { it.toItemDTO() }
    }

    private fun sandwichList(): List<ItemDTO> {
        _menuDetails.value.let {
            sandwichDetails.value = it.sandwichDTOS
        }
        return sandwichDetails.value.map { it.toItemDTO() }
    }

    private fun drinksList(): List<ItemDTO> {
        _menuDetails.value.let {
            drinksDetails.value = it.softDrinkDTOS
        }
        return drinksDetails.value.map { it.toItemDTO() }
    }

    fun getListBySection(@SectionType type: Int): List<ItemDTO> {
        return when (type) {
            SectionType.KOTA -> kotaList()
            SectionType.CHIPS -> chipsList()
            SectionType.ICE_CREAM -> iceCreamList()
            SectionType.SALADS -> saladsList()
            SectionType.SANDWICH -> sandwichList()
            SectionType.DRINKS -> drinksList()
            else -> kotaList()
        }
    }

    fun onAction(action: ArijengOverviewAction) {
        when (action) {
            ArijengOverviewAction.OnProfileClick -> {
            }

            ArijengOverviewAction.OnLogoutClick -> {

            }

            ArijengOverviewAction.OnViewActiveOrderClick -> {

            }

            ArijengOverviewAction.OnCardItemClick -> {

            }
        }
    }

}