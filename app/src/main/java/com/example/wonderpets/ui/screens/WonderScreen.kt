package com.example.wonderpets.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wonderpets.data.WonderItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun WonderScreen(
    onNavigateToAdd: () -> Unit,
    productList: List<WonderItem>
) {
    var selectedCategory by remember { mutableStateOf("Todos") }

    val filteredList = productList.filter {
        selectedCategory == "Todos" || it.location == selectedCategory
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mi Cocina") }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAdd,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar", tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* Acción de Inicio */ }, modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.Home, contentDescription = "Inicio")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* Acción de Ajustes */ }, modifier = Modifier.weight(1f)) {
                        Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { /* TODO: Implementar búsqueda */ },
                placeholder = { Text("Buscar productos...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = MaterialTheme.shapes.large
            )

            Spacer(modifier = Modifier.height(16.dp))

            // botones de categorías
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { selectedCategory = "Todos" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCategory == "Todos") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    )
                ) { Text("Todos") }

                Button(
                    onClick = { selectedCategory = "Refri" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCategory == "Refri") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    )
                ) { Text("Refri") }

                Button(
                    onClick = { selectedCategory = "Alacena" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCategory == "Alacena") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    )
                ) { Text("Alacena") }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // titulo de la lista (cambia dinámicamente)
            Text(
                text = if (selectedCategory == "Todos") "Todos los productos" else "En $selectedCategory",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredList) { item ->
                    WonderItemCard(item)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Vence pronto",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) { // lista de productos
                items(productList) { item ->
                    WonderItemCard(item)
                }
            }
        }
    }
}

@Composable
fun WonderItemCard(item: WonderItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Solo quedan ${item.quantity} unidades",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Vence el: ${item.expirationDate}",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}