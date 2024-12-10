package com.example.nutlicii.data.model

import java.util.Date

data class FoodRequestAdd(
    val nama: String,
    val category: String,
    val calories: String?=null,
    val sugar:String?=null,
    val fats: String?=null,
    val salt: String?=null,
    val date_added:Date,
)
