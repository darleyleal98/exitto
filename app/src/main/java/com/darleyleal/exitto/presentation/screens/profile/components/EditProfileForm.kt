package com.darleyleal.exitto.presentation.screens.profile.components

import DatePickerField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.darleyleal.exitto.domain.entity.Sex
import com.darleyleal.exitto.domain.entity.User
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileForm(
    modifier: Modifier = Modifier,
    user: User? = null,                       // Opcional: preencher campos existentes
    onSubmit: (User) -> Unit = {}              // Callback para salvar os dados
) {
    // ---------- Estados do formulário ----------
    var name by rememberSaveable { mutableStateOf(user?.name ?: "") }
    var dateOfBirth by rememberSaveable { mutableStateOf(user?.dateOfBirthday ?: LocalDate.now()) }
    var height by rememberSaveable { mutableStateOf(user?.heightCm?.toString() ?: "") }
    var weight by rememberSaveable { mutableStateOf(user?.weightKg?.toString() ?: "") }

    // ---------- Dropdown para Sexo ----------
    var expanded by remember { mutableStateOf(false) }
    var selectedSex by rememberSaveable { mutableStateOf(user?.sex ?: Sex.MALE) }
    val sexOptions = Sex.entries.toTypedArray()

    // ---------- DatePicker ----------
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = dateOfBirth.atStartOfDay(ZoneId.systemDefault())
            .toInstant().toEpochMilli()
    )

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

        // DATE OF BIRTH
        DatePickerField(
            label = "Date of Birth",
            selectedDate = dateOfBirth,
            onDateSelected = { dateOfBirth = it }
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}