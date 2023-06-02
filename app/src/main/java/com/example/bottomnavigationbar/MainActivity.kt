package com.example.bottomnavigationbar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.ui.theme.BottomNavigationBarTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMaterialScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationBarTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {}
                val navController = rememberNavController()

                Scaffold(bottomBar={
                    BottomNavigationBar(
                        items = listOf(BottomNavItem(name="Today",route="Today",icon= Icons.Default.Home),
                            BottomNavItem(name="Journey",route="Journey",icon=Icons.Default.Home),
                            BottomNavItem(name="My Shoes",route="My Shoes",icon=Icons.Default.Home),
                            BottomNavItem(name="Reports",route="Reports",icon=Icons.Default.Home)
                            ),
                        navController = navController,
                        onItemClick = {navController.navigate(it.route)}
                    )
                })
                {
                    Navigation(navController)
                }
            }
        }
    }

@Composable
fun Navigation(navController: NavHostController)
{
    NavHost(navController = navController, startDestination ="home" ){
        composable("Today"){
            TodayScreen()
        }
        composable("Journey"){
            JourneyScreen()
        }
        composable("My Shoes"){
            MyShoesScreen()
        }
        composable("Reports"){
            ReportsScreen()
        }
    }
}

@Composable
fun TodayScreen()
{
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text("Today Screen")
    }
}
@Composable
fun JourneyScreen()
{
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text("Journey Screen")
    }
}
@Composable
fun MyShoesScreen()
{
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text("My Shoes Screen")
    }
}

@Composable
fun ReportsScreen()
{
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text("Reports Screen")
    }
}


@Composable
fun BottomNavigationBar(items:List<BottomNavItem>, navController: NavController,
                        modifier: Modifier=Modifier, onItemClick: (BottomNavItem) -> Unit)
{
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(modifier=modifier,backgroundColor= Color.DarkGray,elevation=5.dp)
    {
        items.forEach { item->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(selected = false, onClick = { onItemClick(item) },
                selectedContentColor = Color.DarkGray,
                unselectedContentColor = Color.LightGray,
                icon={
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = item.name)
                        Text(text= item.name, textAlign = TextAlign.Center, fontSize = 12.sp)
                    }
                }
                )
        }
    }
}}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    BottomNavigationBarTheme {
//    }
//}