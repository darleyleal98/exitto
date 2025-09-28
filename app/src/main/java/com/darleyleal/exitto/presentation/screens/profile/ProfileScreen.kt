package com.darleyleal.exitto.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.exitto.presentation.core.theme.RichBlack
import com.darleyleal.exitto.presentation.screens.profile.components.EditProfileForm
import com.darleyleal.exitto.presentation.screens.profile.components.ProfileAvatar
import com.darleyleal.exitto.presentation.screens.profile.components.ProfileSectionCards

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var showIsEditableBottomSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    Scaffold(
        content = { innerPadding ->
            Box(
                modifier = modifier
                    .background(RichBlack)
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding(),
                        start = 8.dp,
                        end = 8.dp
                    )
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileAvatar()
                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp),
                        text = "Darley Leal",
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    OutlinedButton(
                        onClick = {
                            showIsEditableBottomSheet = !showIsEditableBottomSheet
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(text = "Edit profile", fontSize = 16.sp)
                    }
                    Spacer(
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    ProfileSectionCards()
                    if (showIsEditableBottomSheet) {
                        ModalBottomSheet(
                            modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding()),
                            onDismissRequest = { showIsEditableBottomSheet = false },
                            sheetState = sheetState,
                            containerColor = RichBlack
                        ) {
                            ProfileAvatar(isEditable = true)
                            EditProfileForm()
                        }
                    }
                }
            }
        }
    )
}