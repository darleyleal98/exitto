package com.darleyleal.exitto.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darleyleal.exitto.presentation.components.AppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            AppBar()
        },
        content = { innerPadding ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .height(142.dp)
                    .padding(horizontal = 8.dp)
            ) {
                Text("Teste")
            }
        }
    )
}