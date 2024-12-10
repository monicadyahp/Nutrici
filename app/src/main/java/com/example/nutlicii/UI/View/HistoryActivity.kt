package com.example.nutlicii.UI.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutlicii.R
import com.example.nutlicii.UI.Adapter.HistoryAdapter
import com.example.nutlicii.data.model.HistoryItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HistoryActivity : AppCompatActivity() {

    private lateinit var dateRangeTextView: TextView
    private lateinit var historyRecyclerView: RecyclerView
    private var currentDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        dateRangeTextView = findViewById(R.id.date_range)
        historyRecyclerView = findViewById(R.id.history_recycler_view)
        val btnAddfood: FloatingActionButton = findViewById<FloatingActionButton>(R.id.food_add)
        btnAddfood.setOnClickListener{
            val intent = Intent(this, FoodAddActivity::class.java)
            startActivity(intent)
            finish()
        }
        historyRecyclerView.layoutManager = LinearLayoutManager(this)

        val historyList = listOf(
            HistoryItem(
                date = "5 Oktober 2024",
                title = "Teh Pucuk Harum Dingin",
                type = "Drink",
                description = "Free sugar",
                icon = "A"
            )
        )

        // Set up the adapter with the sample data
        val adapter = HistoryAdapter(historyList) { historyItem ->
            // Handle the item click here
            val intent = Intent(this, DetailsFoodActivity::class.java).apply {
                putExtra("EXTRA_NAME", historyItem.title)
                putExtra("EXTRA_CATEGORY", historyItem.type)
                putExtra("EXTRA_DATE", historyItem.date)
                putExtra("EXTRA_DESCRIPTION", historyItem.description)
                putExtra("EXTRA_ICON", historyItem.icon)
            }
            startActivity(intent)
        }
        historyRecyclerView.adapter = adapter

        updateDate()

        findViewById<TextView>(R.id.prev_arrow).setOnClickListener {
            shiftDate(-1)
        }

        findViewById<TextView>(R.id.next_arrow).setOnClickListener {
            shiftDate(1)
        }
    }

    private fun updateDate() {
        val dateFormat = SimpleDateFormat("E dd MMMM yyyy", Locale.getDefault())
        dateRangeTextView.text = dateFormat.format(currentDate.time)
    }

    private fun shiftDate(days: Int) {
        currentDate.add(Calendar.DAY_OF_MONTH, days)
        updateDate()

    }
}
