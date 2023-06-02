package com.example.bottomnavigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.ui.theme.BottomNavigationBarTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationBarTheme {
                val navController = rememberNavController()
                val selectedItem = remember { mutableStateOf(0) }
                val routes = listOf("Today", "Journey", "My Shoes", "Reports")

                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(text = "Example}") })
                    },
                    bottomBar = {
                        BottomNavigationBar(navController = navController, selectedItem = selectedItem, routes = routes)
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = "Today") {
                            composable("Today") {
                                TodayScreen()
                            }
                            composable("Journey") {
                                JourneyScreen()
                            }
                            composable("My Shoes") {
                                MyShoesScreen()
                            }
                            composable("Reports") {
                                ReportsScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TodayScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Today Screen")
    }
}

@Composable
fun JourneyScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Journey Screen")
    }
}

@Composable
fun MyShoesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("My Shoes Screen")
    }
}

@Composable
fun ReportsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Reports Screen")
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    selectedItem: MutableState<Int>,
    routes: List<String>
) {
    NavigationBar {
        routes.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem.value == index,
                onClick = {
                    selectedItem.value = index
                    navController.navigate(item) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    when (item) {
                        "Today" -> Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = null
                        )
                        "Journey" -> Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = null
                        )
                        "My Shoes" -> Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null

                        )
                        "Reports" -> Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = null
                        )
                    }
                },
                label = { Text(text = item) }
            )
        }
    }
}


