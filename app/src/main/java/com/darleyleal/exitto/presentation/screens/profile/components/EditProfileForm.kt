package com.darleyleal.exitto.presentation.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.exitto.domain.entity.Sex
import com.darleyleal.exitto.domain.entity.User
import com.darleyleal.exitto.presentation.components.DateField
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileForm(
    modifier: Modifier = Modifier,
    user: User? = null,
    onSubmit: (User) -> Unit = {}
) {
    var name by rememberSaveable { mutableStateOf(user?.name ?: "") }
    var dateOfBirth by rememberSaveable { mutableStateOf(user?.dateOfBirthday ?: LocalDate.now()) }
    var height by rememberSaveable { mutableStateOf(user?.heightCm?.toString() ?: "") }
    var weight by rememberSaveable { mutableStateOf(user?.weightKg?.toString() ?: "") }

    var expanded by remember { mutableStateOf(false) }
    var selectedSex by rememberSaveable { mutableStateOf(user?.sex ?: Sex.MALE) }
    val sexOptions = Sex.entries.toTypedArray()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // NAME
        OutlinedTextField(
            value = name,
            onValueChange = { input ->
                name = input
            },
            label = { Text("Neme") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // SEX
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedSex.name,
                onValueChange = {},
                readOnly = true,
                label = { Text("Sex") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                sexOptions.forEach { sex ->
                    DropdownMenuItem(
                        text = { Text(sex.name) },
                        onClick = {
                            selectedSex = sex
                            expanded = false
                        }
                    )
                }
            }
        }

        DateField(
            label = "Date of Birth",
            selectedDate = dateOfBirth,
            onDateSelected = { newDate ->
                dateOfBirth = newDate
            }
        )

        // HEIGHT
        OutlinedTextField(
            value = height,
            onValueChange = { input ->
                if (input.all { it.isDigit() || it == '.' }) height = input
            },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // WEIGHT
        OutlinedTextField(
            value = weight,
            onValueChange = { input ->
                if (input.all { it.isDigit() || it == '.' }) weight = input
            },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // SUBMIT BUTTON
        Button(
            modifier = Modifier.height(52.dp).fillMaxWidth(),
            onClick = {
                if (name.isNotBlank() && height.isNotBlank() && weight.isNotBlank()) {
                    onSubmit(
                        User(
                            id = user?.id ?: "", // Se estiver editando, preserva ID
                            name = name,
                            dateOfBirthday = dateOfBirth,
                            sex = selectedSex,
                            heightCm = height.toDoubleOrNull() ?: 0.0,
                            weightKg = weight.toDoubleOrNull() ?: 0.0
                        )
                    )
                }
            },
        ) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null)
            Spacer(modifier = Modifier.padding(start = 8.dp))
            Text("Save", fontSize = 20.sp)
        }
    }
}