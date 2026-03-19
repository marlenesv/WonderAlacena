package com.example.wonderpets.data

data class WonderItem(
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val expirationDate: String,
    val location: String
)