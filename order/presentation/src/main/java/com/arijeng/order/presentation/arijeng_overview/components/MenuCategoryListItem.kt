@file:OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class
)

package com.arijeng.order.presentation.arijeng_overview.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.arijeng.core.domain.arijeng_overview.dto.ItemDTO
import com.arijeng.core.presentation.designsystem.AddIcon
import com.arijeng.core.presentation.designsystem.ArijengTheme
import com.arijeng.core.presentation.designsystem.RemoveIcon
import com.arijeng.core.presentation.designsystem.components.WhiteFilledRoundIconButton
import com.arijeng.order.presentation.R
import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewAction
import com.arijeng.order.presentation.arijeng_overview.ArijengOverviewViewModel
import com.arijeng.order.presentation.arijeng_overview.model.ChipsMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.FavouriteMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.FireFighterMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.KotaMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.MealTherapyUi
import com.arijeng.order.presentation.arijeng_overview.model.PopularMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.SoftDrinksUi
import com.arijeng.order.presentation.arijeng_overview.model.TopCategoryUi
import com.arijeng.order.presentation.arijeng_overview.ui.ItemSection
import com.arijeng.order.presentation.arijeng_overview.ui.defaultSectionList
import com.arijeng.order.presentation.viewmodel.SharedViewModel
import org.koin.androidx.compose.koinViewModel


/**
 * Created by {Bennette Molepo} on {2024/08/10}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Composable
fun MenuCategoryListItem(
    topCategoryUi: List<TopCategoryUi>,
    popularMealsUi: List<PopularMealsUi>,
    favouriteMealsUi: FavouriteMealsUi,
    kotaMealsUi: KotaMealsUi,
    chipsMealsUi: ChipsMealsUi,
    mealTherapyUi: MealTherapyUi,
    fireFighterMealsUi: FireFighterMealsUi,
    softDrinksUi: SoftDrinksUi,
    onItemClick: () -> Unit,
    onAction: (ArijengOverviewAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        LazyColumn(
            modifier = Modifier
                .height(1000.dp)
                .clickable(
                    onClick = {

                    }
                )
                .padding(top = 16.dp, bottom = 16.dp, start = 6.dp, end = 6.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                TopCategoryRow(topCategoryUi,modifier,onAction)
                MenuCategoryFilterChip(menuCategory = "Kota",modifier)
                PopularMealsCategoryRow(popularMealsUi, modifier)
                FavouriteMealsCategoryRow(favouriteMealsUi, modifier)
            }

            items(defaultSectionList){section ->
                KotaMealsCategoryRow(itemSection = section,modifier = modifier,onAction)
            }
        }
    }
}

@Composable
private fun TopCategoryRow(
    topCategoryUi: List<TopCategoryUi>,
    modifier: Modifier = Modifier,
    onAction: (ArijengOverviewAction) -> Unit,
) {
    val sharedViewModel: SharedViewModel = koinViewModel()
    Row(
        modifier = modifier
            .padding(6.dp, top = 6.dp,bottom = 8.dp),
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text =  stringResource(id = R.string.popular_categories),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(topCategoryUi){ index, topMenuCategory->
            TopCategoryItemImage(topMenuCategory=topMenuCategory,index,onAction)
        }
    }
}

@Composable
private fun TopCategoryItemImage(
    topMenuCategory: TopCategoryUi,
    itemIndex: Int,
    onAction: (ArijengOverviewAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .height(100.dp)
            .padding(end = 12.dp, bottom = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                0.5.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colorScheme.secondary)
            .clickable(onClick = {
                Log.d("TOPCATEGORY_CLICK", "Top Category is clicked at $itemIndex")
            }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        SubcomposeAsyncImage(
            model = topMenuCategory.itemPictureUrl,
            contentDescription = stringResource(id = R.string.category_image),
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                //.aspectRatio(16 / 9f)
                .padding(top = 5.dp)
                .clip(RoundedCornerShape(15.dp)),
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
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 6.dp)
            ,
            text = topMenuCategory.itemName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun MenuCategoryFilterChip(
    menuCategory: String,
    modifier: Modifier = Modifier
) {
    val filterMenuItems = listOf(
        "Kota","Chips","Salads","Sandwich","Ice Cream","Soft Drinks"
    )
    Spacer(modifier = Modifier.width(3.dp))
    LazyRow(
        modifier = modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {

        itemsIndexed(filterMenuItems){ index, menuCategory->
            Column(
                modifier = modifier
                    .clickable(onClick = {
                        Log.d("CATEGORY", "Category is clicked at $index")
                    })
                    .background(MaterialTheme.colorScheme.background)
                    .padding(start = 6.dp, end = 30.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = menuCategory,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }

}

@Composable
private fun PopularMealsCategoryRow(popularMealsUi: List<PopularMealsUi>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(6.dp, top = 15.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text =  stringResource(id = R.string.popular_meals),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(popularMealsUi) { index, item ->
            PopularMeals(
                item.itemPictureUrl,
                item.itemName,
                item.ratings,
                itemIndex = index,
                modifier = modifier.animateItemPlacement()
            )
        }
    }
}
@Composable
private fun PopularMeals(
    imageUrl: String?,
    itemName: String,
    ratings: String,
    itemIndex: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(150.dp)
            .height(165.dp)
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.Start
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = stringResource(id = R.string.category_image),
            alignment = Alignment.TopCenter,
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(end = 8.dp, top = 5.dp),
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
        Text(
            modifier = modifier
                .padding(start = 10.dp, top = 15.dp)
                .align(Alignment.Start),
            text = itemName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
       /* Text(
            text = ratings,
            modifier = modifier
                .padding(start = 10.dp, top = 8.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surfaceTint
        )*/

    }
}

@Composable
private fun FavouriteMealsCategoryRow(favouriteMealsUi: FavouriteMealsUi, modifier: Modifier = Modifier) {
    val favouriteMealListTest = listOf(
        FavouriteMealsUi(
            id = favouriteMealsUi.id,
            itemPictureUrl = favouriteMealsUi.itemPictureUrl,
            itemName = favouriteMealsUi.itemName,
            itemDescription = favouriteMealsUi.itemDescription,
            itemPrice = favouriteMealsUi.itemPrice,
            itemSpecialPercentage = favouriteMealsUi.itemSpecialPercentage
        ),
        FavouriteMealsUi(
            id = favouriteMealsUi.id,
            itemPictureUrl = favouriteMealsUi.itemPictureUrl,
            itemName = favouriteMealsUi.itemName,
            itemDescription = favouriteMealsUi.itemDescription,
            itemPrice = favouriteMealsUi.itemPrice,
            itemSpecialPercentage = favouriteMealsUi.itemSpecialPercentage
        ),
        FavouriteMealsUi(
            id = favouriteMealsUi.id,
            itemPictureUrl = favouriteMealsUi.itemPictureUrl,
            itemName = favouriteMealsUi.itemName,
            itemDescription = favouriteMealsUi.itemDescription,
            itemPrice = favouriteMealsUi.itemPrice,
            itemSpecialPercentage = favouriteMealsUi.itemSpecialPercentage
        ),
        FavouriteMealsUi(
            id = favouriteMealsUi.id,
            itemPictureUrl = favouriteMealsUi.itemPictureUrl,
            itemName = favouriteMealsUi.itemName,
            itemDescription = favouriteMealsUi.itemDescription,
            itemPrice = favouriteMealsUi.itemPrice,
            itemSpecialPercentage = favouriteMealsUi.itemSpecialPercentage
        )
    )
    val viewModel:ArijengOverviewViewModel = koinViewModel()
    val favouriteMealList = viewModel.getListBySection(1).reversed().dropLast(3)
    Row(
        modifier = modifier
            .padding(6.dp, top = 15.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text =  stringResource(id = R.string.favourite_meals),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(favouriteMealList) { index,item ->
            FavouriteMeals(
                item.imageUrl,
                item.itemName,
                item.itemDescription,
                item.itemPrice,
                "15% OFF",
                itemIndex = index,
                modifier = modifier.animateItemPlacement()
            )
        }
    }
}

@Composable
private fun FavouriteMeals(
    imageUrl: String?,
    itemName: String,
    itemDescription: String,
    itemPrice: Double,
    itemSpecialPercentage: String,
    itemIndex: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(250.dp)
            .height(115.dp)
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = stringResource(id = R.string.category_image),
                alignment = Alignment.TopCenter,
                modifier = modifier
                    .width(115.dp)
                    .height(100.dp)
                    .padding(end = 2.dp, top = 5.dp),
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
            Column(
                modifier = modifier
                    .padding(start = 6.dp),
                horizontalAlignment = Alignment.Start
            ){
                Row(
                    modifier = modifier
                        .padding(start = 50.dp)
                        .width(100.dp)
                        .clip(shape = RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 1.dp))
                        .background(color = MaterialTheme.colorScheme.primary),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = modifier
                            .padding(start = 12.dp),
                        text = itemSpecialPercentage,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    modifier = modifier
                        .padding(top = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = itemName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = itemDescription,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier
                        .padding(top = 3.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "R${itemPrice}0",
                    modifier = modifier
                        .padding(top = 4.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun KotaMealsCategoryRow(
    itemSection: ItemSection,
    modifier: Modifier,
    onAction: (ArijengOverviewAction) -> Unit
) {
    val sharedViewModel:SharedViewModel = koinViewModel()
    val viewModel:ArijengOverviewViewModel = koinViewModel()
    val lazyItems = viewModel.getListBySection(itemSection.type)

    Row(
        modifier = modifier
            .padding(6.dp, top = 10.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text =  stringResource(id = itemSection.name),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

    Column(
        modifier = Modifier
            .background(Color.Transparent)
    ){
        LazyRow(
            modifier = modifier
                /*.clickable(onClick = {
                    onAction(ArijengOverviewAction.OnCardItemClick)
                    Log.d("OVER_VIEW_SCREEN", "clicked inside overview screen 1111")
                })*/
        ) {
            itemsIndexed(lazyItems) {index, item ->
                MealDetailsCardRow(index,onAction,productItem = item){
                    sharedViewModel.setArijengItem(it)
                    //onAction(ArijengOverviewAction.OnCardItemClick)
                    Log.d("OVER_VIEW_SCREEN", "set arijeng item here...")
                }
            }
        }
    }

}



@Composable
private fun MealDetailsCardRow(
    itemIndex: Int,
    onAction: (ArijengOverviewAction) -> Unit,
    productItem: ItemDTO,
    onItemClick: (ItemDTO) -> Unit = {},
) {
    var quantityVisible by remember { mutableStateOf(false) }
    var quantityValue by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier
            .width(140.dp)
            .height(150.dp)
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable(onClick = {
                onAction(ArijengOverviewAction.OnCardItemClick)
            }),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .width(110.dp)
                .padding(start = 12.dp)
                .clip(RoundedCornerShape(15.dp))
                ,
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
           Row(
                modifier = Modifier
                    .width(110.dp)
                    .padding(start = 1.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(if (quantityVisible) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
               AnimatedVisibility(
                   modifier = Modifier,
                   visible = quantityVisible,
                   enter = fadeIn(animationSpec = tween(2000)),
                   exit = fadeOut(animationSpec = tween(2000))
               ){

                   WhiteFilledRoundIconButton(
                       imageVector = RemoveIcon,
                       onClick = {
                           quantityValue-=1
                           Log.d("AD_OR_REMOCE_CLICK","We minus quantity now")
                       }
                   )

                   Text(
                       modifier = Modifier
                           .padding(start = 45.dp),
                       text = quantityValue.toString(),
                       textAlign = TextAlign.Center,
                       color = MaterialTheme.colorScheme.secondary,
                       maxLines = 1
                   )
               }
               WhiteFilledRoundIconButton(
                   imageVector = AddIcon,
                   onClick = {
                       quantityValue+=1
                       quantityVisible = !quantityVisible
                       Log.d("AD_OR_REMOCE_CLICK","We adding quantity now")
                   }
               )
           }

        }
        SubcomposeAsyncImage(
            model = productItem.imageUrl,
            contentDescription = stringResource(id = R.string.category_image),
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(end = 2.dp, top = 1.dp),
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
        Text(
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
                .align(Alignment.Start),
            text = productItem.itemName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = "R${productItem.itemPrice}0",
            modifier = Modifier
                .padding(start = 10.dp, top = 2.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun ExpandAddOrRemoveItemsRow(){

}
@Composable
private fun ChipsMeals(
    imageUrl: String?,
    itemName: String,
    modifier: Modifier = Modifier
) {

}

@Composable
private fun TraditionalMeals(
    imageUrl: String?,
    itemName: String,
    modifier: Modifier = Modifier
) {

}

@Composable
private fun SandwichMeals(
    imageUrl: String?,
    itemName: String,
    modifier: Modifier = Modifier
) {

}

@Composable
private fun GreenSaladsMeals(
    imageUrl: String?,
    itemName: String,
    modifier: Modifier = Modifier
) {

}

@Composable
private fun DesertWithIceCreams(
    imageUrl: String?,
    itemName: String,
    modifier: Modifier = Modifier
) {

}

@Composable
private fun SoftDrinks(
    imageUrl: String?,
    itemName: String,
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
private fun TopCategoryListItemPreview() {
    ArijengTheme {
        MenuCategoryListItem(
            topCategoryUi = listOf(
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
            ),
            popularMealsUi = listOf( PopularMealsUi(
                id = "123",
                itemPictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s",
                itemName = "Burger",
                ratings = "*****",
            )),
            favouriteMealsUi = FavouriteMealsUi(
                id = "123",
                itemPictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s",
                itemName = "Burger",
                itemPrice = "R45.00",
                itemDescription = "A heavy meal that treats you nice.",
                itemSpecialPercentage = "15% OFF"
            ),
            kotaMealsUi =  KotaMealsUi(
                id = "1",
                itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/kota%2Fkt_ten.png?alt=media&token=9cbfbae7-21ca-41fd-9ea4-c9438c13f896",
                itemName = "Burger",
                itemPrice = "R35.00"
            ),
            chipsMealsUi =  ChipsMealsUi(
                id = "1",
                itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/kota%2Fkt_ten.png?alt=media&token=9cbfbae7-21ca-41fd-9ea4-c9438c13f896",
                itemName = "Burger",
                itemPrice = "R60.00"
            ),
            mealTherapyUi =  MealTherapyUi(
                id = "6",
                itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/sandwich%2Fsand_four.png?alt=media&token=4dabc9b4-52fa-48b7-9a8b-2bcc2fe81701",
                itemName = "Therapy meal",
                itemPrice = "R35.00"
            ),
            fireFighterMealsUi =  FireFighterMealsUi(
                id = "6",
                itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/chips%2Fcp_two.png?alt=media&token=ba137f55-ebc8-4de6-bfe0-a8423015950e",
                itemName = "Crispy fries",
                itemPrice = "R35.00"
            ),
            softDrinksUi =  SoftDrinksUi(
                id = "6",
                itemPictureUrl = "https://firebasestorage.googleapis.com/v0/b/arijeng.appspot.com/o/chips%2Fcp_two.png?alt=media&token=ba137f55-ebc8-4de6-bfe0-a8423015950e",
                itemName = "Crispy fries",
                itemPrice = "R35.00"
            ),
            onItemClick = {},
            onAction = {}
        )
    }
}