package com.example.nutlicii.data.repository

import com.example.nutlicii.data.model.ApiResponse
import com.example.nutlicii.data.model.FoodRequestAdd
import data.Remote.ApiService
import data.local.db.AppDatabase
import data.model.Userdata
import retrofit2.Response
import java.util.Date
class FoodRepository(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {
    suspend fun addFood(
        nama: String,
        category: String,
        calories: String?,
        sugar: String?,
        fats: String?,
        salt: String?,
        date_added: Date
    ): Response<ApiResponse<Userdata>> {
        // Ambil user dari database
        val user = appDatabase.userDao().getUser()
        val username = user?.username ?: throw IllegalStateException("User not found")
        val token=user?.token ?:throw IllegalStateException("Token not found")
        val foodAddRequest = FoodRequestAdd(nama, category, calories, sugar, fats, salt, date_added)
        return apiService.addFood(token, username, foodAddRequest)
    }
}
