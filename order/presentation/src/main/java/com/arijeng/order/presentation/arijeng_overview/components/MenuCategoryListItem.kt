@file:OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.arijeng.order.presentation.arijeng_overview.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.arijeng.core.presentation.designsystem.ArijengTheme
import com.arijeng.order.presentation.R
import com.arijeng.order.presentation.arijeng_overview.model.FavouriteMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.PopularMealsUi
import com.arijeng.order.presentation.arijeng_overview.model.TopCategoryUi


/**
 * Created by {Bennette Molepo} on {2024/08/10}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Composable
fun MenuCategoryListItem(
    topCategoryUi: List<TopCategoryUi>,
    popularMealsUi: PopularMealsUi,
    favouriteMealsUi: FavouriteMealsUi,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        Column(
            modifier = modifier
                .clickable(
                    onClick = {
                        onItemClick()
                    }
                )
                .padding(top = 16.dp, bottom = 16.dp, start = 6.dp, end = 6.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopCategoryRow(topCategoryUi)
            MenuCategoryFilterChip(menuCategory = "Kota",modifier)
            PopularMealsCategoryRow(popularMealsUi, modifier)
            FavouriteMealsCategoryRow(favouriteMealsUi, modifier)
        }
    }
}

@Composable
private fun TopCategoryRow(topCategoryUi: List<TopCategoryUi>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(topCategoryUi){ index, topMenuCategory->
            TopCategoryItemImage(
                imageUrl = topMenuCategory.itemPictureUrl,
                itemName = topMenuCategory.itemName,
                itemIndex = index,
                modifier = modifier.animateItemPlacement()
            )
        }
    }
}

@Composable
private fun TopCategoryItemImage(
    imageUrl: String?,
    itemName: String,
    itemIndex: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(90.dp)
            .height(100.dp)
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable(onClick = {
                Log.d("TOPCATEGORY_CLICK", "Top Category is clicked at $itemIndex")
            }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = stringResource(id = R.string.category_image),
            alignment = Alignment.TopCenter,
            modifier = modifier
                .height(50.dp)
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
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
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 6.dp)
            ,
            text = itemName,
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
private fun PopularMealsCategoryRow(popularMealsUi: PopularMealsUi, modifier: Modifier = Modifier) {
    val popularMealList = listOf(
        PopularMealsUi(
            id = popularMealsUi.id,
            itemPictureUrl = popularMealsUi.itemPictureUrl,
            itemName = popularMealsUi.itemName,
            ratings = popularMealsUi.ratings
        ),
        PopularMealsUi(
            id = popularMealsUi.id,
            itemPictureUrl = popularMealsUi.itemPictureUrl,
            itemName = popularMealsUi.itemName,
            ratings = popularMealsUi.ratings
        ),
        PopularMealsUi(
            id = popularMealsUi.id,
            itemPictureUrl = popularMealsUi.itemPictureUrl,
            itemName = popularMealsUi.itemName,
            ratings = popularMealsUi.ratings
        ),
        PopularMealsUi(
            id = popularMealsUi.id,
            itemPictureUrl = popularMealsUi.itemPictureUrl,
            itemName = popularMealsUi.itemName,
            ratings = popularMealsUi.ratings
        )
    )
    Row(
        modifier = modifier
            .padding(6.dp, top = 15.dp),
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
        items(popularMealList) { item ->
            PopularMeals(
                item.itemPictureUrl,
                item.itemName,
                item.ratings,
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(150.dp)
            .height(175.dp)
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
                .padding(end = 12.dp),
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
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = ratings,
            modifier = modifier
                .padding(start = 10.dp, top = 8.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surfaceTint
        )
    }
}

@Composable
private fun FavouriteMealsCategoryRow(favouriteMealsUi: FavouriteMealsUi, modifier: Modifier = Modifier) {
    val favouriteMealList = listOf(
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
    Row(
        modifier = modifier
            .padding(6.dp, top = 15.dp),
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
        items(favouriteMealList) { item ->
            FavouriteMeals(
                item.itemPictureUrl,
                item.itemName,
                item.itemDescription,
                item.itemPrice,
                item.itemSpecialPercentage,
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
    itemPrice: String,
    itemSpecialPercentage: String,
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
                    .padding(end = 2.dp),
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
                Text(
                    modifier = modifier
                        .padding(top = 6.dp),
                    text = itemName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = itemDescription,
                    modifier = modifier
                        .padding(top = 8.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.surface
                )
                Text(
                    text = itemPrice,
                    modifier = modifier
                        .padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }


    }
}

@Composable
private fun KotaMeals() {

}

@Composable
private fun ChipsMeals() {

}

@Composable
private fun TraditionalMeals() {

}

@Composable
private fun SandwichMeals() {

}

@Composable
private fun GreenSaladsMeals() {

}

@Composable
private fun DesertWithIceCreams() {

}

@Composable
private fun SoftDrinks() {

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
            popularMealsUi = PopularMealsUi(
                id = "123",
                itemPictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s",
                itemName = "Burger",
                ratings = "*****"
            ),
            favouriteMealsUi = FavouriteMealsUi(
                id = "123",
                itemPictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s",
                itemName = "Burger",
                itemPrice = "R45.00",
                itemDescription = "A heavy meal that treats you nice.",
                itemSpecialPercentage = "15% OFF"
            ),
            onItemClick = {}
        )
    }
}