@file:OptIn(ExperimentalMaterial3Api::class)

package com.arijeng.order.presentation.arijeng_overview.shopping_cart

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.arijeng.core.presentation.designsystem.ArijengIcon
import com.arijeng.core.presentation.designsystem.ArijengTheme
import com.arijeng.core.presentation.designsystem.CartIcon
import com.arijeng.core.presentation.designsystem.LogoutIcon
import com.arijeng.core.presentation.designsystem.ProfileIcon
import com.arijeng.core.presentation.designsystem.components.ArijengActionButton
import com.arijeng.core.presentation.designsystem.components.ArijengFloatingActionButton
import com.arijeng.core.presentation.designsystem.components.ArijengScaffold
import com.arijeng.core.presentation.designsystem.components.ArijengToolbar
import com.arijeng.core.presentation.designsystem.components.ArijengTopBarWithIcons
import com.arijeng.core.presentation.designsystem.components.util.DropDownItem
import com.arijeng.order.presentation.R
import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewAction
import com.arijeng.order.presentation.more_item_details.AddMinusButtons
import org.koin.androidx.compose.koinViewModel


/**
 * Created by {Bennette Molepo} on {2024/09/04}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

@Composable
fun ShoppingCartScreenRoot(
    navController: NavController,
    viewModel: ShoppingCartViewModel = koinViewModel(),
    onCheckOutClick: () -> Unit,
){
    ShoppingCartTopAppBarDetails(navController,viewModel.state,onCheckOutClick)
}

@Composable
fun ShoppingCartTopAppBarDetails(
    navController: NavController,
    state: ShoppingCartState,
    onCheckOutClick: () -> Unit
){
    ArijengTopBarWithIcons(
        onClick = {
            navController.popBackStack()
        },
        cartOnClick = {

        },
        enabled = true,
        isCartScreen = true,
        text = "Cart"
    ) {
        ShoppingCartScreen(
            navController,
            state,
            onAction = {action->
                when(action){
                    is ShoppingCartAction.OnGoToCheckoutClick -> onCheckOutClick()
                    else -> Unit
                }
            }
        )
    }
}
@Composable
fun ShoppingCartScreen(
    navController: NavController,
    state: ShoppingCartState,
    onAction: (ShoppingCartAction)-> Unit
){

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ){
            Column(
                modifier = Modifier
                    .padding(top = 75.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                LazyColumn(
                    modifier = Modifier
                        .height(600.dp)
                        .clickable(
                            onClick = {

                            }
                        )
                        .padding(top = 10.dp, bottom = 2.dp)
                ) {
                    item {
                        MiddleDetails()
                    }
                }
                BottomDetails(navController,state,onAction)
            }
        }
}
@Composable
fun BottomDetails(
    navController: NavController,
    state: ShoppingCartState,
    onAction: (ShoppingCartAction)-> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp, start = 6.dp, bottom = 2.dp, end = 6.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
            ArijengActionButton(
                text = "CHECK OUT",
                isLoading = state.isCheckingOut,
                enabled = state.canCheckOut && !state.isCheckingOut,
                onClick = {
                    onAction(ShoppingCartAction.OnGoToCheckoutClick)
                }
            )
    }

}
@Composable
fun MiddleDetails(
) {

    LazyColumn(
        modifier = Modifier
            .height(630.dp)
    ) {
        items(
            items = listOf(
                "Kota 1",
                "Kota 2",
                "Kota 3",
                "Kota 3",
                "Kota 3",
                "Kota 3",
                "Kota 3"
            )
        ) {
            ItemsRow(modifier = Modifier, items = it)
        }
    }

}
@SuppressLint("RememberReturnType")
@Composable
fun ItemsRow(
    modifier: Modifier,
    items: String,
    onItemClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp, top = 15.dp, bottom = 2.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(items)
            },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(
                modifier = modifier
                    .padding(12.dp)
                    .height(70.dp)
                    .width(50.dp),
                shape = RectangleShape
            ) {
                SubcomposeAsyncImage(
                    model = "itemData.imageUrl",
                    contentDescription = stringResource(id = R.string.cart_item_image),
                    alignment = Alignment.TopCenter,
                    modifier = modifier
                        .fillMaxWidth()
                        .size(150.dp)
                        .padding(end = 2.dp, top = 1.dp)
                        .clip(RectangleShape),
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.primaryContainer
                            )
                        }
                    },
                    error = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.errorContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.error_couldnt_load_image),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
            }

            Column(
                modifier = modifier
                    .padding(2.dp, end = 5.dp)
            ) {
                Text(
                    modifier = Modifier.width(125.dp),
                    text = "Items my test test test fdhjfgkh hdghfasdhgfdf syufsfgh done3",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "R25,00",
                    style = MaterialTheme.typography.titleSmall
                )


            }

            Row(
                modifier = modifier
                    .padding(horizontal = 3.dp, vertical = 3.dp),
                horizontalArrangement = Arrangement.End
            ) {
                AddMinusButtons(modifier)
            }
        }
    }
}

@Preview
@Composable
private fun ArijengOverviewScreenPreview() {
    ArijengTheme {
        ShoppingCartScreen(
            navController = rememberNavController(),
            state = ShoppingCartState(),
            onAction = {}
        )
    }
}