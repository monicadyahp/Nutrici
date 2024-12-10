package com.example.nutlicii.UI.View

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nutlicii.R

class DetailsFoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityfood_detail_food)

        // Ambil data yang dikirim melalui Intent
        val name = intent.getStringExtra("EXTRA_NAME")
        val category = intent.getStringExtra("EXTRA_CATEGORY")
        val calories = intent.getStringExtra("EXTRA_CALORIES")
        val protein = intent.getStringExtra("EXTRA_PROTEIN")
        val sugar = intent.getStringExtra("EXTRA_SUGAR")
        val fat = intent.getStringExtra("EXTRA_FAT")
        val salt = intent.getStringExtra("EXTRA_SALT")
        val grade = intent.getStringExtra("EXTRA_GRADE")

        // Set nilai ke TextView
        findViewById<TextView>(R.id.title).text = name
        findViewById<TextView>(R.id.grade_value).text = grade
        findViewById<TextView>(R.id.details_card).apply {
            findViewById<TextView>(R.id.details_card).text = name
        }

        // Tombol Edit dan Delete
        findViewById<Button>(R.id.edit_button).setOnClickListener {
            // Tambahkan logika Edit di sini
        }

        findViewById<Button>(R.id.delete_button).setOnClickListener {
            // Tambahkan logika Delete di sini
        }
    }
}
