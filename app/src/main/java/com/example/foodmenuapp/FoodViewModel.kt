package com.example.foodmenuapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*



class FoodViewModel : ViewModel() {
    var foodList by mutableStateOf<List<FoodItem>>(emptyList())
    var errorMessage by mutableStateOf("")

    init {
        fetchFoods()
    }

    fun fetchFoods() {
        viewModelScope.launch {
            try {
                foodList = RetrofitClient.apiService.getFoods()
                Log.d("FoodViewModel", "Fetched ${foodList.size} items:")
                foodList.forEach {
                    Log.d("FoodViewModel", "Food: ${it.name} - ${it.description}")
                }


            } catch (e: Exception) {
                errorMessage = e.message ?: "Unknown error"
                Log.d("FoodViewModel", errorMessage)
            }
        }
    }

    fun addFood(food: FoodItem) {
        viewModelScope.launch {
            RetrofitClient.apiService.createFood(food)
            fetchFoods()
        }
    }

    fun updateFood(food: FoodItem) {
        viewModelScope.launch {
            RetrofitClient.apiService.updateFood(food.id, food)
            fetchFoods()
        }
    }

    fun deleteFood(id: Int) {
        viewModelScope.launch {
            RetrofitClient.apiService.deleteFood(id)
            fetchFoods()
        }
    }
}
