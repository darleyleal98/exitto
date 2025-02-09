package com.darleyleal.exitto.presentation.navigation.bottom_navigation

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedItemIndex: (Int) -> Unit
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        bottomNavigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedItemIndex(index)
                    selectedIndex = index
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = contentColorFor(
                            backgroundColor = MaterialTheme.colorScheme.background
                        )
                    )
                },
                label = {
                    Text(text = item.label, fontWeight = FontWeight.W700)
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors()
            )
        }
    }
}