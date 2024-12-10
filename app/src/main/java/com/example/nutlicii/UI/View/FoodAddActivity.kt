package com.example.nutlicii.UI.View
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nutlicii.R
import com.example.nutlicii.data.repository.FoodRepository
import com.example.nutlicii.ui.viewmodel.FoodViewModel
import data.Remote.NutliciiBaseApi
import data.local.db.AppDatabase
import java.util.Date

class FoodAddActivity : AppCompatActivity() {
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        appDatabase = AppDatabase.getDatabase(this)
        val foodRepository = FoodRepository(NutliciiBaseApi.getApiService(), appDatabase)
        foodViewModel = FoodViewModel(foodRepository)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityfood_add)

        val etFooodname=findViewById<EditText>(R.id.et_food_name)
        val etFoodCategory=findViewById<EditText>(R.id.et_category)
        val etFoodCaloriese=findViewById<EditText>(R.id.et_calories)
        val etFoodSugar=findViewById<EditText>(R.id.et_sugar)
        val etFooodFat=findViewById<EditText>(R.id.et_sugar)
        val etFoodSalt=findViewById<EditText>(R.id.et_salt)
        val BtnFoodadd=findViewById<Button>(R.id.btn_save_foods)

        BtnFoodadd.setOnClickListener{
            val fooname=etFooodname.text.toString()
            val foodCategori=etFoodCategory.text.toString()
            val foodCaloris=etFoodCaloriese.text.toString()
            val foodSugar=etFoodSugar.text.toString()
            val foodFat=etFooodFat.text.toString()
            val foodSalt=etFoodSalt.text.toString()
            foodViewModel.addFood(
                name = fooname,
                category = foodCategori,
                calories = foodCaloris,
                sugar = foodSugar,
                fats = foodFat,
                salt = foodSalt,
                dateAdded = Date(),
                onSuccess = { response ->
                    Toast.makeText(this, "Food added successfully", Toast.LENGTH_SHORT).show()
                },
                onError = { error ->
                    Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            )
        }

    }
}