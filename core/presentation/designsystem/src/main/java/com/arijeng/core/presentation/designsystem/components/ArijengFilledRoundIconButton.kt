package com.arijeng.core.presentation.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


/**
 * Created by {Bennette Molepo} on {2024/08/19}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
@Composable
fun WhiteFilledRoundIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick:  () -> Unit,
    tint: Color =  MaterialTheme.colorScheme.primary
) {
   Card(modifier = modifier
        .padding(top = 3.dp, start = 6.dp, end = 3.dp, bottom = 3.dp),
        shape = RoundedCornerShape(100),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onBackground,
            disabledContentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            modifier = modifier.size(20.dp)
                .clickable { onClick.invoke()},
            imageVector = imageVector,
            contentDescription = "Plus or minus icon",
            tint = tint
        )
    }
}