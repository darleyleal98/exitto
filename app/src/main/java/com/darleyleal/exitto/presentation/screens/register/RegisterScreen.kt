package com.darleyleal.exitto.presentation.screens.register

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.exitto.presentation.core.theme.DarkLavander
import com.darleyleal.exitto.presentation.core.theme.RichBlack
import com.darleyleal.exitto.presentation.core.theme.Typography
import com.darleyleal.exitto.presentation.provider.ViewModelKey
import com.darleyleal.exitto.presentation.provider.ViewModelProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onPopBackStack: () -> Unit,
    onNavigateToMainScreen: () -> Unit,
    viewModelProvider: ViewModelProvider
) {
    val systemUiController = rememberSystemUiController()
    val paddingValues = WindowInsets.statusBars.asPaddingValues()

    val registerViewModel = viewModelProvider.getViewModel(ViewModelKey.REGISTER) as RegisterViewModel

    val context = LocalContext.current
    val isSuccess by registerViewModel.isSuccess.collectAsState()
    val isFailure by registerViewModel.isFailure.collectAsState()
    val errorMessage by registerViewModel.errorMessage.collectAsState()

    val email by registerViewModel.email.collectAsState()
    var isEmailInvalid by remember { mutableStateOf(false) }
    var isEmailFormatInvalid by remember { mutableStateOf(false) }

    val password by registerViewModel.password.collectAsState()
    var isPasswordInvalid by remember { mutableStateOf(false) }
    var isPasswordTooShort by remember { mutableStateOf(false) }

    val confirmPassword by registerViewModel.confirmPassword.collectAsState()
    var isConfirmPasswordInvalid by remember { mutableStateOf(false) }
    var isPasswordMismatch by remember { mutableStateOf(false) }

    var showPassword by rememberSaveable { mutableStateOf(false) }
    var showConfirmPassword by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isSuccess, isFailure, errorMessage) {
        when {
            isSuccess -> {
                Toast.makeText(context, "User registered successfully!", Toast.LENGTH_SHORT).show()
                onNavigateToMainScreen()
            }

            isFailure -> {
                Toast.makeText(context, errorMessage ?: "Registration failed. Please try again.", Toast.LENGTH_LONG).show()
            }
        }
    }

    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = true
        )
    }

    Scaffold(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding()
            ),
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                title = {
                    Text(
                        text = "Sign up",
                        fontSize = 48.sp,
                        color = Color.DarkGray.copy(alpha = 0.6f)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onPopBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = RichBlack,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding() + 50.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { input ->
                        registerViewModel.updateEmail(email = input)
                        if (input.isNotEmpty()) {
                            isEmailInvalid = input.trim().isEmpty()
                            isEmailFormatInvalid = registerViewModel.validateIfEmailIsInvalid(text = input)
                        } else {
                            isEmailInvalid = false
                            isEmailFormatInvalid = false
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        unfocusedTextColor = Color.Gray,
                        focusedTextColor = Color.Gray
                    ),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(start = 8.dp),
                            imageVector = Icons.Filled.Email,
                            contentDescription = null,
                            tint = Color.DarkGray.copy(alpha = 0.6f),
                        )
                    },
                    label = {
                        Text(
                            text = "E-mail",
                            color = Color.DarkGray.copy(alpha = 0.6f),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    isError = isEmailInvalid,
                    shape = RoundedCornerShape(100.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                )

                if (isEmailInvalid) {
                    Text(
                        text = "Email is required",
                        color = Color.Red,
                        style = Typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )
                }

                if (isEmailFormatInvalid && !isEmailInvalid) {
                    Text(
                        text = "Email doesn't match",
                        color = Color.Red,
                        style = Typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(top = 8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { input ->
                        registerViewModel.updatePassword(password = input)
                        if (input.isNotEmpty()) {
                            isPasswordInvalid = input.trim().isEmpty()
                            isPasswordTooShort = input.length < 6
                        } else {
                            isPasswordInvalid = false
                            isPasswordTooShort = false
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        unfocusedTextColor = Color.Gray,
                        focusedTextColor = Color.Gray
                    ),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(start = 8.dp),
                            imageVector = Icons.Filled.Password,
                            contentDescription = null,
                            tint = Color.DarkGray.copy(alpha = 0.6f),
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.padding(end = 8.dp),
                            onClick = {
                                showPassword = !showPassword
                            }
                        ) {
                            Icon(
                                imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = Color.DarkGray.copy(alpha = 0.6f),
                            )
                        }
                    },
                    isError = isPasswordInvalid,
                    label = {
                        Text(
                            text = "Password",
                            color = Color.DarkGray.copy(alpha = 0.6f),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(100.dp),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                )

                if (isPasswordInvalid) {
                    Text(
                        text = "Password is required",
                        color = Color.Red,
                        style = Typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )
                }

                if (isPasswordTooShort && !isPasswordInvalid) {
                    Text(
                        text = "Password must be at least 6 characters",
                        color = Color.Red,
                        style = Typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(top = 12.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { input ->
                        registerViewModel.updateConfirmPassword(confirmPassword = input)
                        if (input.isNotEmpty()) {
                            isConfirmPasswordInvalid = input.trim().isEmpty()
                            isPasswordMismatch = input != password
                        } else {
                            isConfirmPasswordInvalid = false
                            isPasswordMismatch = false
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        unfocusedTextColor = Color.Gray,
                        focusedTextColor = Color.Gray
                    ),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(start = 8.dp),
                            imageVector = Icons.Filled.Password,
                            contentDescription = null,
                            tint = Color.DarkGray.copy(alpha = 0.6f),
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.padding(end = 8.dp),
                            onClick = {
                                showConfirmPassword = !showConfirmPassword
                            }
                        ) {
                            Icon(
                                imageVector = if (showConfirmPassword) Icons.Filled.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = Color.DarkGray.copy(alpha = 0.6f),
                            )
                        }
                    },
                    isError = isConfirmPasswordInvalid,
                    label = {
                        Text(
                            text = "Confirm Password",
                            color = Color.DarkGray.copy(alpha = 0.6f),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(100.dp),
                    visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                )

                if (isConfirmPasswordInvalid) {
                    Text(
                        text = "Confirm Password is required",
                        color = Color.Red,
                        style = Typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )
                }

                if (isPasswordMismatch && !isConfirmPasswordInvalid) {
                    Text(
                        text = "Passwords do not match",
                        color = Color.Red,
                        style = Typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(top = 12.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(62.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkLavander
                    ),
                    onClick = {
                        if (registerViewModel.validateAllFields()) {
                            registerViewModel.createUserWithEmailAndPasswordInFirebase(email = email, password = password)
                        } else {
                            isEmailInvalid = email.trim().isEmpty()
                            isEmailFormatInvalid = registerViewModel.validateIfEmailIsInvalid(email)
                            isPasswordInvalid = password.trim().isEmpty()
                            isPasswordTooShort = password.length < 6
                            isConfirmPasswordInvalid = confirmPassword.trim().isEmpty()
                            isPasswordMismatch = confirmPassword != password

                            Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text(
                        text = "Sign Up",
                        style = Typography.bodyLarge,
                        color = Color.White,
                        fontSize = 26.sp
                    )
                }
            }
        }
    )
}