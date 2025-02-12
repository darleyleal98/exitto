package com.darleyleal.exitto.presentation.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    Column(modifier = modifier.padding(paddingValues)) {
        Text(text = "Profile screen")
    }
}