package com.darleyleal.exitto.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.darleyleal.exitto.R
import com.darleyleal.exitto.presentation.components.AuthenticationCard
import com.darleyleal.exitto.presentation.components.ExittoImageCard

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        ExittoImageCard()
        AuthenticationCard(title = "Welcome", dontHaveAnAccount = true)
    }
}