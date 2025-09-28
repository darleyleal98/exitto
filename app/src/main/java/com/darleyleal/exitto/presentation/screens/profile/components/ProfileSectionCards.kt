package com.darleyleal.exitto.presentation.screens.profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.darleyleal.exitto.R

@Composable
fun ProfileSectionCards(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        item {
            ProfileCardItem(
                icon = painterResource(R.drawable._8_up_rating),
                title = "Age",
                value = "27 years"
            )
        }
        item {
            ProfileCardItem(
                icon = painterResource(R.drawable.cake),
                title = "Date of Birthday",
                value = "March 21, 1998"
            )
        }
        item {
            ProfileCardItem(
                icon = painterResource(R.drawable.person),
                title = "Sex",
                value = "Male"
            )
        }
        item {
            ProfileCardItem(
                icon = painterResource(R.drawable.height),
                title = "Height",
                value = "171 cm"
            )
        }
        item {
            ProfileCardItem(
                icon = painterResource(R.drawable.scale),
                title = "Weight",
                value = "66 kg"
            )
        }
    }
}