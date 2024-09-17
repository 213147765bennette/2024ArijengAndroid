package com.arijeng.core.domain.arijeng_overview.dto

import com.arijeng.order.data.dto.FridaySundaySnackDTO


/**
 * Created by {Bennette Molepo} on {2024/08/15}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@kotlinx.serialization.Serializable
data class MenuDTO(
    var kotaDTOS: List<KotaDTO>,
    var chipsDTOS: List<ChipsDTO>,
    var iceCreamDTOS: List<IceCreamDTO>,
    var saladDTOS: List<SaladDTO>,
    var softDrinkDTOS: List<SoftDrinkDTO>,
    var mealTherapyDTOS: List<MealTherapyDTO>,
    var squardComboDTOS: List<SquardComboDTO>,
    var sandwichDTOS: List<SandwichDTO>,
    var fridaySundaySnackDTOS: List<FridaySundaySnackDTO>
)
