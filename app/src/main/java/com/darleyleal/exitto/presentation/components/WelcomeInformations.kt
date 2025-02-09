package com.darleyleal.exitto.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.exitto.R
import com.darleyleal.exitto.data.model.User

@Composable
fun WelcomeInformations(modifier: Modifier = Modifier, user: User? = null) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 22.dp, start = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = modifier.size(width = 64.dp, height = 64.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Image(
                painterResource(R.drawable.pexels_cottonbro),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
               Column {
                   Text(
                       text = "Hello, ${user?.name}",
                       fontSize = 24.sp,
                       fontWeight = FontWeight.W700,
                       color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                   )
                   Text(
                       text = "Good morning",
                       fontSize = 28.sp,
                       fontWeight = FontWeight.W700,
                       color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                   )
               }
                IconButton(
                    modifier = modifier.size(width = 48.dp, height = 48.dp),
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null
                    )
                }
            }
        }
    }
}