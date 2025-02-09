package com.darleyleal.exitto.presentation.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.darleyleal.exitto.presentation.components.GetStartedCard
import com.darleyleal.exitto.presentation.components.ExittoImageCard

@Composable
fun StartScreen(modifier: Modifier = Modifier, onNavigationToMainScreen: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        ExittoImageCard()
        GetStartedCard(getStartedButtonOnClick = { onNavigationToMainScreen() })
    }
}