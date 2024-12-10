package com.example.nutlicii.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutlicii.data.model.ApiResponse
import com.example.nutlicii.data.repository.FoodRepository
import data.model.Userdata
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.Date

class FoodViewModel(
    private val foodRepository: FoodRepository
) : ViewModel() {

    fun addFood(
        name: String,
        category: String,
        calories: String?,
        sugar: String?,
        fats: String?,
        salt: String?,
        dateAdded: Date,
        onSuccess: (Response<ApiResponse<Userdata>>) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = foodRepository.addFood(
                    nama = name,
                    category = category,
                    calories = calories,
                    sugar = sugar,
                    fats = fats,
                    salt = salt,
                    date_added = dateAdded
                )
                if (response.isSuccessful) {
                    onSuccess(response)
                } else {
                    onError("Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                onError(e.message ?: "Unknown error occurred")
            }
        }
    }
}
