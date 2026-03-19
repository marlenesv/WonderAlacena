package com.example.wonderpets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.wonderpets.ui.theme.WonderPetsTheme
import com.example.wonderpets.ui.screens.WonderScreen
import com.example.wonderpets.data.WonderItem

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WonderPetsTheme {

                val navController = rememberNavController()

                val dummyList = listOf(
                    WonderItem(1, "Leche", 2, "20/10/2026"),
                    WonderItem(2, "Arroz", 1, "01/12/2026"),
                    WonderItem(3, "Huevos", 12, "25/10/2026")
                )

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {

                    composable("home") {
                        WonderScreen(
                            onNavigateToAdd = {
                                navController.navigate("add")
                            },
                            productList = dummyList
                        )
                    }

                    composable("add") {
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = { Text("Agregar producto") },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            navController.popBackStack()
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowBack,
                                                contentDescription = "Regresar"
                                            )
                                        }
                                    }
                                )
                            }
                        ) { padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                Text("Pantalla en construcción")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    WonderPetsTheme {
        Text("Preview")
    }
}