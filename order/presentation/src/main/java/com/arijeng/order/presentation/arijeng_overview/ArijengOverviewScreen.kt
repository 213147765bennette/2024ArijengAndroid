@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.arijeng.order.presentation.arijeng_overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arijeng.core.presentation.designsystem.ActiveOrderIcon
import com.arijeng.core.presentation.designsystem.ArijengIcon
import com.arijeng.core.presentation.designsystem.ArijengTheme
import com.arijeng.core.presentation.designsystem.CartIcon
import com.arijeng.core.presentation.designsystem.LogoutIcon
import com.arijeng.core.presentation.designsystem.SearchIcon
import com.arijeng.core.presentation.designsystem.components.ArijengFloatingActionButton
import com.arijeng.core.presentation.designsystem.components.ArijengScaffold
import com.arijeng.core.presentation.designsystem.components.ArijengSearchTextField
import com.arijeng.core.presentation.designsystem.components.ArijengToolbar
import com.arijeng.core.presentation.designsystem.components.util.DropDownItem
import com.arijeng.order.presentation.R
import com.arijeng.order.presentation.arijeng_overview.components.MenuCategoryListItem
import com.arijeng.order.presentation.arijeng_overview.model.FavouriteMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.PopularMealsUi
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
    viewModel: ArijengOverviewViewModel = koinViewModel()
) {
    ArijengOverviewScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                ArijengOverviewAction.OnViewActiveOrderClick -> onViewActiveOrder()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

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
                        icon = CartIcon,
                        title = stringResource(id = R.string.cart)
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout)
                    )
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(ArijengOverviewAction.OnCartClick)
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
                icon = ActiveOrderIcon,
                onClick = {
                    onAction(ArijengOverviewAction.OnViewActiveOrderClick)
                })
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
                    popularMealsUi = PopularMealsUi(
                        id = "123",
                        itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/kota%2Fbig_two.png?alt=media&token=e69bf715-fd72-4fa3-b08a-494d3465dcfc",
                        itemName = "Burger",
                        ratings = "*****"
                    ),
                    favouriteMealsUi = FavouriteMealsUi(
                        id = "123",
                        itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/kota%2Fbig_two.png?alt=media&token=e69bf715-fd72-4fa3-b08a-494d3465dcfc",
                        itemName = "Burger",
                        itemPrice = "R45.00",
                        itemDescription = "A heavy meal that treats you nice.",
                        itemSpecialPercentage = "15% OFF"
                    ),
                    onItemClick = {

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
            itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/topCategory%2Ftop_burger.png?alt=media&token=0fdd041d-c131-4e2d-9ba5-92a629f053f2",
            itemName = "Burger"
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