package com.example.nutlicii.UI.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nutlicii.R
import com.example.nutlicii.data.ViewModel.AuthViewModel
import com.example.nutlicii.data.ViewModel.AuthViewModelFactory
import com.example.nutlicii.data.repository.AuthRepository
import data.Remote.NutliciiBaseApi
import data.local.db.AppDatabase
import kotlinx.coroutines.launch
class RegisterActivity : AppCompatActivity() {
    private val AuthViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(
            AuthRepository(
                NutliciiBaseApi.getApiService(),
                AppDatabase.getDatabase(this)
            )
        )
    }
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        appDatabase = AppDatabase.getDatabase(this)

        val etName = findViewById<EditText>(R.id.etName)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etRepeatPassword = findViewById<EditText>(R.id.etRepeatPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val name = etName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            val repeatPassword = etRepeatPassword.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty()) {
                if (password == repeatPassword) {
                    AuthViewModel.registerUser(username, password, email, name, repeatPassword)
                } else {
                    showErrorMessage("Passwords do not match")
                }
            } else {
                showErrorMessage("Please fill in all fields")
            }
        }

        observeRegisterResult()
    }

    private fun observeRegisterResult() {
        AuthViewModel.registerResult.observe(this) { response ->
            if (response.isSuccessful) {
                val user = response.body()?.data
                if (user != null) {
                    showErrorMessage("Register successful")
                    lifecycleScope.launch {
                        val intent = Intent(this@RegisterActivity, LandingActivity::class.java)
                        intent.putExtra("user_data", user)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    showErrorMessage("Register failed: No user data received.")
                }
            } else {
                showErrorMessage("Register failed: ${response.message()}")
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
