package com.darleyleal.exitto.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.darleyleal.exitto.R
import com.darleyleal.exitto.data.data_store.UserStore

@Composable
fun AuthenticationCard(modifier: Modifier = Modifier, getStartedButtonOnClick: () -> Unit) {

    val context = LocalContext.current
    val store = UserStore(context)
    val token = store.getAccessToken.collectAsState(initial = false)

    RoundedTopCard(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = (-22).dp)
            .zIndex(1f)
            .fillMaxHeight()
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier.padding(start = 4.dp),
                text = stringResource(R.string.manage_your_health_with_ease),
                fontSize = 32.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black.copy(alpha = 0.4f)
            )
            Button(
                modifier = modifier
                    .padding(start = 44.dp, end = 44.dp, top = 16.dp)
                    .fillMaxWidth()
                    .height(62.dp)
                    .fillMaxHeight(),
                onClick = {
                    getStartedButtonOnClick()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.2f))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Get Started", textAlign = TextAlign.Center, fontSize = 24.sp)
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                }
            }
        }
    }
}