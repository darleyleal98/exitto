package com.darleyleal.exitto.presentation.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.exitto.R
import com.darleyleal.exitto.presentation.core.theme.DarkLavander
import com.darleyleal.exitto.presentation.core.theme.Typography
import com.darleyleal.exitto.presentation.provider.ViewModelProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(modifier: Modifier = Modifier, onNavigateToRegisterScreen: () -> Unit, auth: FirebaseAuth, viewModelProvider: ViewModelProvider) {
    val systemUiController = rememberSystemUiController()
    val paddingValues = WindowInsets.statusBars.asPaddingValues()

    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var showPassword by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }

    Scaffold(
        topBar = {
            Text(
                modifier = Modifier.padding(top = paddingValues.calculateTopPadding() + 40.dp, start = 16.dp),
                text = "exitto",
                fontSize = 68.sp,
                color = Color.DarkGray.copy(alpha = 0.6f)
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .imePadding()
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(R.drawable.pexels_cottonbro_7026522),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxWidth()
                    )
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .offset(y = -(72).dp)
                            .fillMaxWidth()
                            .border(
                                width = 8.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                            ),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp)
                            ) {
                                Text(
                                    text = "Sign in",
                                    textAlign = TextAlign.Center,
                                    fontSize = 48.sp,
                                    color = Color.DarkGray.copy(alpha = 0.6f),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp, bottom = 16.dp)
                                )

                                OutlinedTextField(
                                    value = email,
                                    onValueChange = { input ->
                                        email = input
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        unfocusedTextColor = Color.Black,
                                        focusedTextColor = Color.Black,
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
                                    shape = RoundedCornerShape(100.dp),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    singleLine = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(72.dp)
                                )

                                Spacer(modifier = Modifier.padding(top = 8.dp))

                                OutlinedTextField(
                                    value = password,
                                    onValueChange = { input ->
                                        password = input
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        unfocusedTextColor = Color.Black,
                                        focusedTextColor = Color.Black,
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

                                Spacer(modifier = Modifier.padding(top = 12.dp))

                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(62.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = DarkLavander
                                    ),
                                    onClick = {

                                    }
                                ) {
                                    Text(
                                        text = "Login",
                                        style = Typography.bodyLarge,
                                        color = Color.White,
                                        fontSize = 26.sp
                                    )
                                }

                                Spacer(modifier = Modifier.padding(top = 12.dp))

                                Box(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    OutlinedButton(
                                        modifier = Modifier
                                            .height(62.dp)
                                            .fillMaxWidth()
                                            .align(Alignment.Center),
                                        onClick = {
                                            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                                        }
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.google_button_sign),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.size(52.dp)
                                        )

                                        Spacer(modifier = Modifier.padding(start = 2.dp))

                                        Text(
                                            text = "Sign in with Google",
                                            color = Color.Black,
                                            style = Typography.bodyMedium,
                                            fontSize = 22.sp
                                        )
                                    }
                                }

                                Box(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.BottomCenter)
                                            .padding(bottom = 16.dp)
                                    ) {
                                        Text(
                                            text = "Don't have a account?",
                                            style = Typography.bodySmall,
                                            color = Color.Black
                                        )
                                        TextButton(
                                            onClick = {
                                                onNavigateToRegisterScreen()
                                            }
                                        ) {
                                            Text(
                                                text = "Sign up",
                                                style = Typography.bodySmall,
                                                color = DarkLavander
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}