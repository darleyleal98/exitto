package com.darleyleal.exitto.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import java.time.LocalDate
import java.time.ZoneId
import com.darleyleal.exitto.presentation.utils.millisToLocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    label: String,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    useDialog: Boolean = true
) {
    var showPicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate?.atStartOfDay(
            ZoneId.systemDefault()
        )?.toInstant()?.toEpochMilli()
    )

    OutlinedTextField(
        value = selectedDate?.toString() ?: "",
        onValueChange = { },
        label = { Text(label) },
        readOnly = true,
        placeholder = { Text("YYYY-MM-DD") },
        trailingIcon = {
            IconButton(onClick = { showPicker = true }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Open date picker")
            }
        },
        modifier = Modifier.fillMaxWidth()
    )

    if (showPicker) {
        if (useDialog) {
            DatePickerDialog(
                onDismissRequest = { showPicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        val newDate = millisToLocalDate(datePickerState.selectedDateMillis)
                        if (newDate != null) onDateSelected(newDate)
                        showPicker = false
                    }) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { showPicker = false }) { Text("Cancel") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        } else {
            Popup(onDismissRequest = { showPicker = false }, alignment = Alignment.TopStart) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    Column {
                        DatePicker(state = datePickerState)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextButton(onClick = {
                                val newDate = millisToLocalDate(datePickerState.selectedDateMillis)
                                if (newDate != null) onDateSelected(newDate)
                                showPicker = false
                            }) { Text("OK") }
                            TextButton(onClick = { showPicker = false }) { Text("Cancel") }
                        }
                    }
                }
            }
        }
    }
}