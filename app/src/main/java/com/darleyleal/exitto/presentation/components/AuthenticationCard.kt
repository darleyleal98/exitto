package com.darleyleal.exitto.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun AuthenticationCard(modifier: Modifier = Modifier, title: String, dontHaveAnAccount: Boolean) {
    var emailImput by rememberSaveable { mutableStateOf("") }

    RoundedTopCard(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = (-22).dp)
            .zIndex(1f)
            .fillMaxHeight()
    ) {
        Column {

        }
    }
}