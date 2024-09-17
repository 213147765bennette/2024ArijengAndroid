package com.arijeng.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arijeng.core.presentation.designsystem.ArijengGray40
import com.arijeng.core.presentation.designsystem.ArijengTheme
import com.arijeng.core.presentation.designsystem.ArijengIcon


/**
 * Created by {Bennette Molepo} on {2024/06/05}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

@Composable
fun ArijengFloatingActionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    iconSize: Dp = 25.dp,
    enabled: Boolean = true,
    totalPrice: String,
    isLoading: Boolean,

) {

        Button(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = ArijengGray40
            ),
            shape = RoundedCornerShape(85f),
            modifier = modifier
                .height(IntrinsicSize.Min)
                .width(150.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(iconSize).padding(end = 8.dp)
                    )
                    Text(
                        text = totalPrice,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .alpha(if(isLoading) 0f else 1f),
                        fontWeight = FontWeight.Medium
                    )
                }

            }
        }

      /*  Box(modifier = Modifier
            .size(50.dp)
            .clip(RectangleShape)
            .background(MaterialTheme.colorScheme.primary)
            .padding(12.dp),
            contentAlignment = Alignment.Center
        ){
            Button(
                onClick = { }
            ) {

            }
            Icon(imageVector = icon,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(iconSize)
            )
        }*/

}

@Preview
@Composable
private fun ArijengFloatingActionButtonPreview() {
    ArijengTheme {
        ArijengFloatingActionButton(icon = ArijengIcon,
            onClick = { /*TODO*/ },
            totalPrice = "R345.00",
            isLoading = true
        )
    }
}