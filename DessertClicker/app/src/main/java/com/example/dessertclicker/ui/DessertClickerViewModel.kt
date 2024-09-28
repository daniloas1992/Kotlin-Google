package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DessertClickerUiState())

    val uiState:StateFlow<DessertClickerUiState> =_uiState.asStateFlow()

    init {
        val dessertStart = getActualDessert()
        _uiState.value = DessertClickerUiState(revenue = 0, dessertsSold = 0, currentDessertPrice = dessertStart.price, currentDessertImageId = dessertStart.imageId, productionAmount = 0)
    }

    fun updateDessertState() {

        val actualDessert = getActualDessert()

        _uiState.update { currentState ->
            currentState.copy (
                revenue = currentState.revenue + actualDessert.price,
                dessertsSold = currentState.dessertsSold.inc(),
                productionAmount = currentState.productionAmount.inc(),
                currentDessertImageId = actualDessert.imageId
            )
        }
    }

    private fun getActualDessert() : Dessert {
        return dessertList.last { dessert -> uiState.value.productionAmount >= dessert.startProductionAmount }

    }

}