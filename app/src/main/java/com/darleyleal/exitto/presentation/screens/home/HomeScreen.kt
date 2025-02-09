package com.darleyleal.exitto.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.darleyleal.exitto.presentation.components.WelcomeInformations

@Composable
fun HomeScreen(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    Column(modifier = modifier.padding(paddingValues)) {
       WelcomeInformations()
    }
}