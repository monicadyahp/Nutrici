package com.example.nutlicii.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DashboardData(
    val progress: Progress,
    val nutrients: Nutrients,
    val bmi: String,
    val advice: String
): Parcelable

