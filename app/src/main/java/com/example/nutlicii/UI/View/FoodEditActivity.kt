package com.example.nutlicii.UI.View

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.nutlicii.R
import com.example.nutlicii.UI.Adapter.FoodItem

class FoodEditActivity : AppCompatActivity() {

    // Deklarasi variabel View
    private lateinit var etFoodName: EditText
    private lateinit var etCategory: EditText
    private lateinit var etCalories: EditText
    private lateinit var etSugar: EditText
    private lateinit var etFat: EditText
    private lateinit var etSalt: EditText
    private lateinit var btnSave: Button
    private lateinit var btnNewCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityfood_add)

        // Inisialisasi View
        etFoodName = findViewById(R.id.et_food_name)
        etCategory = findViewById(R.id.et_category)
        etCalories = findViewById(R.id.et_calories)
        etSugar = findViewById(R.id.et_sugar)
        etFat = findViewById(R.id.et_fat)
        etSalt = findViewById(R.id.et_salt)
        btnSave = findViewById(R.id.btn_save_foods)
        btnNewCategory = findViewById(R.id.btn_new)

        // Listener tombol Save
        btnSave.setOnClickListener {
            saveFoodData()
        }

        // Listener tombol New (kategori baru)
        btnNewCategory.setOnClickListener {
            etCategory.setText("") // Kosongkan input kategori
            Toast.makeText(this, "Add new category", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveFoodData() {
        // Ambil nilai dari input EditText
        val foodName = etFoodName.text.toString().trim()
        val category = etCategory.text.toString().trim()
        val calories = etCalories.text.toString().toIntOrNull()
        val sugar = etSugar.text.toString().toIntOrNull()
        val fat = etFat.text.toString().toIntOrNull()
        val salt = etSalt.text.toString().toIntOrNull()

        // Validasi input
        if (foodName.isEmpty() || category.isEmpty() || calories == null || sugar == null || fat == null || salt == null) {
            Toast.makeText(this, "Please fill all fields correctly!", Toast.LENGTH_SHORT).show()
            return
        }

        // Simpan data ke model (bisa diganti dengan database)
        val foodItem = FoodItem(foodName, category, calories, sugar, fat, salt)

        // Simulasi penyimpanan
        Toast.makeText(this, "Food data saved: $foodItem", Toast.LENGTH_LONG).show()

        // Reset input setelah penyimpanan
        clearInputs()
    }

    private fun clearInputs() {
        etFoodName.text.clear()
        etCategory.text.clear()
        etCalories.text.clear()
        etSugar.text.clear()
        etFat.text.clear()
        etSalt.text.clear()
    }
}
