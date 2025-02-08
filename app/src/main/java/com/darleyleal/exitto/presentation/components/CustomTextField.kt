package com.darleyleal.exitto.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    leadingicon: ImageVector
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        value = text,
        onValueChange = { onValueChange(it) },
        label = {
            label?.let {
                Text(text = it)
            }
        },
        leadingIcon = {
            Icon(imageVector = leadingicon, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            
        )
    )
}