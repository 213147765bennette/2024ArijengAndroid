@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.arijeng.order.presentation.arijeng_overview

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arijeng.core.presentation.designsystem.ArijengIcon
import com.arijeng.core.presentation.designsystem.ArijengTheme
import com.arijeng.core.presentation.designsystem.CartIcon
import com.arijeng.core.presentation.designsystem.LogoutIcon
import com.arijeng.core.presentation.designsystem.ProfileIcon
import com.arijeng.core.presentation.designsystem.SearchIcon
import com.arijeng.core.presentation.designsystem.components.ArijengFloatingActionButton
import com.arijeng.core.presentation.designsystem.components.ArijengScaffold
import com.arijeng.core.presentation.designsystem.components.ArijengSearchTextField
import com.arijeng.core.presentation.designsystem.components.ArijengToolbar
import com.arijeng.core.presentation.designsystem.components.util.DropDownItem
import com.arijeng.order.presentation.R
import com.arijeng.order.presentation.arijeng_overview.components.MenuCategoryListItem
import com.arijeng.order.presentation.arijeng_overview.model.ChipsMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.FavouriteMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.FireFighterMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.KotaMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.MealTherapyUi
import com.arijeng.order.presentation.arijeng_overview.model.PopularMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.SoftDrinksUi
import com.arijeng.order.presentation.arijeng_overview.model.TopCategoryUi
import org.koin.androidx.compose.koinViewModel


/**
 * Created by {Bennette Molepo} on {2024/08/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Composable
fun ArijengOverviewScreenRoot(
    onViewActiveOrder: () -> Unit,
    onCardItemClick: () -> Unit,
    viewModel: ArijengOverviewViewModel = koinViewModel()
) {
    ArijengOverviewScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                ArijengOverviewAction.OnViewActiveOrderClick -> onViewActiveOrder()
                ArijengOverviewAction.OnCardItemClick -> onCardItemClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArijengOverviewScreen(
    state: ArijengOverviewState,
    onAction: (ArijengOverviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

    ArijengScaffold(
        topAppBar = {
            ArijengToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.arijeng),
                menuItems = listOf(
                    DropDownItem(
                        icon = ProfileIcon,
                        title = stringResource(id = R.string.profile)
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout)
                    )
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(ArijengOverviewAction.OnProfileClick)
                        1 -> onAction(ArijengOverviewAction.OnLogoutClick)
                    }
                },
                scrollBehavior = scrollBehaviour,
                startContent = {
                    Icon(
                        imageVector = ArijengIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            ArijengFloatingActionButton(
                icon = CartIcon,
                onClick = {
                    onAction(ArijengOverviewAction.OnViewActiveOrderClick)
                },
                isLoading = false,
                totalPrice = "R345.00"
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 32.dp)
                    .padding(top = 60.dp)
            ) {
                ArijengSearchTextField(
                    state = state.mealName,
                    startIcon = SearchIcon,
                    endIcon = null,
                    keyboardType = KeyboardType.Text,
                    hint = stringResource(id = R.string.what_are_you_having),
                    title = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                )

                MenuCategoryListItem(
                    shopByTopCategories(),
                    shopByPopularMeals(),
                    favouriteMealsUi = FavouriteMealsUi(
                        id = "123",
                        itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/kota%2Fbig_two.png?alt=media&token=e69bf715-fd72-4fa3-b08a-494d3465dcfc",
                        itemName = "Burger",
                        itemPrice = "R45.00",
                        itemDescription = "A heavy meal that treats you nice.",
                        itemSpecialPercentage = "15% OFF"
                    ),
                    kotaMealsUi =  KotaMealsUi(
                        id = "6",
                        itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/kota%2Fkt_ten.png?alt=media&token=9cbfbae7-21ca-41fd-9ea4-c9438c13f896",
                        itemName = "Soft Drinks",
                        itemPrice = "R50.00"
                    ),
                    chipsMealsUi =  ChipsMealsUi(
                        id = "6",
                        itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/chips%2Fcp_two.png?alt=media&token=ba137f55-ebc8-4de6-bfe0-a8423015950e",
                        itemName = "Crispy fries",
                        itemPrice = "R35.00"
                    ),
                    mealTherapyUi =  MealTherapyUi(
                        id = "6",
                        itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/sandwich%2Fsand_four.png?alt=media&token=4dabc9b4-52fa-48b7-9a8b-2bcc2fe81701",
                        itemName = "Therapy meal",
                        itemPrice = "R35.00"
                    ),
                    fireFighterMealsUi =  FireFighterMealsUi(
                        id = "6",
                        itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/icecream%2Ficecream_four.jpg?alt=media&token=ced3cafb-7832-47f8-8614-d153d1d3840a",
                        itemName = "Crispy fries",
                        itemPrice = "R35.00"
                    ),
                    softDrinksUi =  SoftDrinksUi(
                        id = "6",
                        itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/drinks%2Fsoda.png?alt=media&token=cca0d529-1d1e-42bb-87ba-739d57894e66",
                        itemName = "Crispy fries",
                        itemPrice = "R35.00"
                    ),
                    onItemClick = {

                    },
                    onAction ={
                        //onAction(ArijengOverviewAction.OnCardItemClick)
                        Log.d("OVER_VIEW_SCREEN","clicked inside overview screen")
                    }
                )

            }
        }

    }
}

@Composable
private fun shopByTopCategories() : List<TopCategoryUi>{
    return listOf(
        TopCategoryUi(
            id = "1",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/topCategory%2Fbunny_chow.png?alt=media&token=4a16eb3c-3c79-43dc-aee2-52efa9da3b0d",
            itemName = "Kota"
        ),
        TopCategoryUi(
            id = "2",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/topCategory%2Ftop_sandwich.png?alt=media&token=45a412cd-506e-4cf3-b5d9-6ab51123e750",
            itemName = "Sandwich"
        ),
        TopCategoryUi(
            id = "3",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/topCategory%2Ftop_fries.png?alt=media&token=825e6e4b-eea1-4ecb-a861-4488b676de64",
            itemName = "Chips"
        ),
        TopCategoryUi(
            id = "4",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/topCategory%2Ftop_greensalad.png?alt=media&token=8d2eb4d7-1670-4a28-8b9c-ec098754abb2",
            itemName = "Salads"
        ),
        TopCategoryUi(
            id = "5",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/topCategory%2Ftop_icecream.png?alt=media&token=08f429e6-c869-4130-b483-5de5947e90b4",
            itemName = "Ice Cream"
        ),
        TopCategoryUi(
            id = "6",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/topCategory%2Ftop_drinks.png?alt=media&token=7fe7641d-f270-4092-9a0a-dd2f6fc19d81",
            itemName = "Soft Drinks"
        )
    )
}

@Composable
private fun shopByPopularMeals(): List<PopularMealsUi>{
    return listOf(
        PopularMealsUi(
            id = "1",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/kota%2Fkota_two.png?alt=media&token=9290ffd1-6117-427e-b41b-e86523cfab9b",
            itemName = "Bronze Kota",
            ratings = "5"
        ),
        PopularMealsUi(
            id = "1",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/kota%2Fkt_nine.png?alt=media&token=c8a8627a-ea19-43e8-b2e7-836f21af6206",
            itemName = "Alluminium Kota",
            ratings = "5"
        ),
        PopularMealsUi(
            id = "1",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/sandwich%2Fsand_eight.png?alt=media&token=431017fa-d974-4f92-b91a-5ec38b1436c2",
            itemName = "Roasted Chicken Sandwich",
            ratings = "5"
        ),
        PopularMealsUi(
            id = "1",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/kota%2Fquarantine%2029.png?alt=media&token=68237125-3ce9-4025-b206-5c429e57aa8b",
            itemName = "Black Diamond Kota",
            ratings = "5"
        ),
        PopularMealsUi(
            id = "1",
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/salads%2Fsalad_nine.png?alt=media&token=b54f004c-513f-459f-9637-f24bad4bfb22",
            itemName = "Fried Russian",
            ratings = "5"
        )
    )
}

@Preview
@Composable
private fun ArijengOverviewScreenPreview() {
    ArijengTheme {
        ArijengOverviewScreen(
            state = ArijengOverviewState(),
            onAction = {}
        )
    }
}