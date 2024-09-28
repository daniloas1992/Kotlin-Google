package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes

data class DessertClickerUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertPrice: Int = 0,
    @DrawableRes val currentDessertImageId: Int = 0,
    val productionAmount: Int = 0
)