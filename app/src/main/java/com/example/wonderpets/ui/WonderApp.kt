package com.example.wonderpets.ui

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wonderpets.data.WonderItem
import com.example.wonderpets.ui.screens.AddProductScreen
import com.example.wonderpets.ui.screens.WonderScreen

@Composable
fun WonderApp() {
    val navController = rememberNavController()

    // lista de prueba
    var productList by remember {
        mutableStateOf(listOf(
            WonderItem(
                id = 1,
                name = "Leche",
                quantity = 2,
                expirationDate = "20/03/2026",
                location = "Refri"
            ),
            WonderItem(
                id = 2,
                name = "Arroz",
                quantity = 1,
                expirationDate = "15/08/2026",
                location = "Alacena"
            )
        ))
    }

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            WonderScreen(
                onNavigateToAdd = { navController.navigate("add") },
                productList = productList
            )
        }

        composable("add") {
            AddProductScreen(
                onNavigateBack = { navController.popBackStack() },
                onSaveProduct = { name, quantity, location, date ->

                    val newItem = WonderItem(
                        name = name,
                        quantity = quantity,
                        expirationDate = date,
                        location = location
                    )

                    productList = productList + newItem
                    navController.popBackStack()
                }
            )
        }
    }
}