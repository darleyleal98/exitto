package com.darleyleal.exitto.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.exitto.R

@Composable
fun ExittoImageCard(modifier: Modifier = Modifier) {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.pexels_cottonbro),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(12f / 18f),
            contentScale = ContentScale.Crop
        )
        Column(modifier = modifier.padding(top = 122.dp, start = 22.dp)) {
            Text(
                text = stringResource(R.string.exitto),
                fontSize = 52.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black.copy(alpha = 0.4f)
            )
            Text(
                modifier = modifier.padding(start = 4.dp),
                text = "Manage your health with ease",
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black.copy(alpha = 0.4f)
            )
        }
    }
}