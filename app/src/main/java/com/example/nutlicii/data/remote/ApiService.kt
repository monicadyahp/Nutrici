package data.Remote

import retrofit2.http.Query
import com.example.nutlicii.data.model.ApiResponse
import com.example.nutlicii.data.model.DashboardResponse
import com.example.nutlicii.data.model.FoodRequestAdd
import com.example.nutlicii.data.model.PromptRequest
import com.example.nutlicii.data.model.PromptResponse
import data.model.LoginRequest
import data.model.RegisterRequest
import data.model.Userdata
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/users/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<ApiResponse<Userdata>>
    @POST("api/users")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<ApiResponse<Userdata>>
    @GET("api/dashboard/{username}")
    suspend fun getDashboardData(
        @Header("Authorization") authorization: String,
        @Path("username") username: String,
        @Query("date") date: String
    ): DashboardResponse
    @POST("api/food/{username}")
    suspend fun addFood(
        @Header("Authorization") authorization: String,
        @Path("username") username: String,
        @Body food: FoodRequestAdd
    ): Response<ApiResponse<Userdata>>
    // Menggunakan endpoint sesuai API Gemini
    @Headers("Content-Type: application/json")
    @POST("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,  // Menggunakan parameter API Key
        @Body request: PromptRequest  // Body request sesuai format JSON
    ): Response<PromptResponse>
}

