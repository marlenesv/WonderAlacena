package com.example.wonderpets.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wonderpets.data.WonderItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WonderScreen(
    onNavigateToAdd: () -> Unit,
    productList: List<WonderItem>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wonder Alacena (${productList.size})") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(productList) { item ->
                WonderItemCard(item)
            }
        }
    }
}

@Composable
fun WonderItemCard(item: WonderItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Cantidad: ${item.quantity}")

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Caduca: ${item.expirationDate}",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}