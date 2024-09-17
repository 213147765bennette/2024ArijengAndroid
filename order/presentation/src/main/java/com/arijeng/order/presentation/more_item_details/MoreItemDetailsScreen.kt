package com.arijeng.order.presentation.more_item_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.arijeng.core.domain.arijeng_overview.dto.ItemDTO
import com.arijeng.core.presentation.designsystem.AddIcon
import com.arijeng.core.presentation.designsystem.RemoveIcon
import com.arijeng.core.presentation.designsystem.components.ArijengTopBarWithIcons
import com.arijeng.core.presentation.designsystem.components.WhiteFilledRoundIconButton
import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewAction
import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewViewModel
import com.arijeng.order.presentation.viewmodel.SharedViewModel
import org.koin.androidx.compose.koinViewModel


/**
 * Created by {Bennette Molepo} on {2023/04/07}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

@Composable
fun MoreItemDetailsScreenRoot(
    navController: NavController,
    onViewActiveOrder: () -> Unit,
    sharedViewModel: SharedViewModel = koinViewModel(),
    viewModel: ArijengOverviewViewModel = koinViewModel()
) {
    MoreDetailsContentTopAppBar(navController, onAction = {action->
        when (action) {
            ArijengOverviewAction.OnViewActiveOrderClick -> onViewActiveOrder()
            else -> Unit
        }
        viewModel.onAction(action)
    }, sharedViewModel)
}

@Composable
fun MoreDetailsContentTopAppBar(
    navController: NavController,
    onAction: (ArijengOverviewAction) -> Unit,
    sharedViewModel: SharedViewModel
) {

    val itemData = sharedViewModel.productItem
    ArijengTopBarWithIcons(
        onClick = {
            navController.popBackStack()
        },
        cartOnClick = {
            onAction(ArijengOverviewAction.OnViewActiveOrderClick)
        },
        enabled = true,
        text = "Details",
        isCartScreen = false
    ) {
        MainDetailsContent(navController,itemData)
    }

}

@Composable
fun MainDetailsContent(
    navController: NavController,
    itemData: ItemDTO?
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 2.dp, end = 2.dp, top = 100.dp),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            item {
                OrderImage(navController, itemData)
            }
        }
    }
}

@Composable
fun OrderImage(
    navController: NavController,
    itemData: ItemDTO?
) {
    LaunchedEffect(key1 = itemData) {
        if (itemData != null) {
            Log.d("MoreItemDetailScreen", itemData.itemName)
            Log.d("MoreItemDetailScreen", itemData.itemDescription)
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //if (itemData != null) {
            SubcomposeAsyncImage(
                model = itemData?.imageUrl,
                contentDescription = stringResource(id = com.arijeng.order.presentation.R.string.more_item_details_image),
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    //.height(80.dp)
                    .size(200.dp)
                    .padding(start = 12.dp, end = 12.dp, top = 12.dp),
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
                            text = stringResource(id = com.arijeng.order.presentation.R.string.error_couldnt_load_image),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
            OrderDetails(Modifier, itemData)

        //}
    }
}

@Composable
fun OrderDetails(
    modifier: Modifier,
    itemData: ItemDTO?
) {

    //if (itemData != null) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 5.dp),
            text = "itemData?.itemName.toString()",
           color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge
        )
    Spacer(modifier = Modifier.size(6.dp))
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            text = "R${itemData?.itemPrice}",
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1,
            style = MaterialTheme.typography.titleLarge
        )
    Spacer(modifier = Modifier.size(6.dp))
        AddMinusButtons(modifier)
    Spacer(modifier = Modifier.size(6.dp))
        ItemDescription(modifier, itemData)
    //}
}

@Composable
fun ItemDescription(
    modifier: Modifier,
    itemData: ItemDTO?
) {

    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 15.dp, end = 12.dp),
        text = "Item Description",
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleMedium
    )

    ///if (itemData != null) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 8.dp),
            text = "itemData?.itemDescription.toString()",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodySmall
        )
    //}
}

@Composable
fun AddMinusButtons(modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 3.dp, vertical = 3.dp),
        horizontalArrangement = Arrangement.End
    ) {
        WhiteFilledRoundIconButton(
            modifier.size(35.dp),
            imageVector = RemoveIcon,
            onClick = {

            }
        )

        Text(
            modifier = modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 12.dp, end = 12.dp),
            text = "1",
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1
        )

        WhiteFilledRoundIconButton(
            modifier.size(35.dp),
            imageVector = AddIcon,
            onClick = {

            }
        )
    }
}

