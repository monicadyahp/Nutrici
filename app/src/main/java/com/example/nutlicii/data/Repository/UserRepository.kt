package com.example.nutlicii.data.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nutlicii.data.model.DashboardResponse
import data.Remote.ApiService
import com.example.nutlicii.data.utils.Result
import data.local.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class UserRepository(private val context: Context, private val apiService: ApiService) {

    // Fungsi untuk mendapatkan username dan token dari Room Database
    private suspend fun getUserFromRoom(): Pair<String?, String?> {
        val userDao = AppDatabase.getDatabase(context).userDao()  // Akses userDao dari AppDatabase
        val user = userDao.getUser()  // Ambil data user dari Room (termasuk username dan token)
        return Pair(user?.username, user?.token)  // Mengembalikan pasangan username dan token
    }

    // Fungsi untuk mendapatkan tanggal saat ini
    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    // Fungsi untuk mengambil data dashboard
    suspend fun getDashboardData(): LiveData<Result<DashboardResponse>> {
        val liveData = MutableLiveData<Result<DashboardResponse>>()
        liveData.value = Result.Loading

        val (username, token) = getUserFromRoom() // Ambil username dan token dari Room Database
        val date = getCurrentDate() // Ambil tanggal saat ini

        if (username == null || token == null) {
            liveData.value = Result.Error("Username or Token is not found")
            return liveData
        }

        try {
            // Mengambil data dashboard dengan username, date, dan token
            val response = withContext(Dispatchers.IO) {
                apiService.getDashboardData("Bearer $token", username, date)
            }
            liveData.value = Result.Success(response)
        } catch (e: Exception) {
            liveData.value = Result.Error("Error: ${e.message}")
        }

        return liveData
    }
}
