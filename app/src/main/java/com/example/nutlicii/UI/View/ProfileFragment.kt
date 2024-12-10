package com.example.nutlicii.UI.View

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.nutlicii.R

class ProfileFragment : Fragment(R.layout.activity_profile) {

    private lateinit var profilePicture: ImageView
    private lateinit var editProfileIcon: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var genderSpinner: Spinner
    private lateinit var activitySpinner: Spinner
    private lateinit var ageEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var logoutButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profilePicture = view.findViewById(R.id.profilePicture)
        editProfileIcon = view.findViewById(R.id.editProfileIcon)
        nameEditText = view.findViewById(R.id.nameEditText)
        emailEditText = view.findViewById(R.id.emailEditText)
        genderSpinner = view.findViewById(R.id.genderSpinner)
        activitySpinner = view.findViewById(R.id.activitySpinner)
        ageEditText = view.findViewById(R.id.ageEditText)
        heightEditText = view.findViewById(R.id.heightEditText)
        weightEditText = view.findViewById(R.id.weightEditText)
        saveButton = view.findViewById(R.id.saveButton)
        logoutButton = view.findViewById(R.id.logoutButton)

        val genderOptions = listOf("Male", "Female")
        val genderAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            genderOptions
        )
        genderSpinner.adapter = genderAdapter


        val activityOptions = listOf("Easy", "Medium", "Hard")
        val activityAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            activityOptions
        )
        activitySpinner.adapter = activityAdapter

        saveButton.setOnClickListener {

        }

        logoutButton.setOnClickListener {

        }

        editProfileIcon.setOnClickListener {

        }
    }
}
