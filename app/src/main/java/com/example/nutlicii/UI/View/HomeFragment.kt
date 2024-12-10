package com.example.nutlicii.ui.view



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import android.widget.TextView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.nutlicii.R
import com.example.nutlicii.UI.View.HistoryActivity
import com.example.nutlicii.UI.View.RegisterActivity
import com.example.nutlicii.data.Repository.UserRepository
import com.example.nutlicii.data.ViewModel.UserViewModel
import com.example.nutlicii.data.ViewModel.UserViewModelFactory
import com.example.nutlicii.data.utils.Result
import data.Remote.ApiService
import data.Remote.NutliciiBaseApi

class HomeFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize UserRepository and UserViewModelFactory
        val apiService = NutliciiBaseApi.getApiService()
        val userRepository = UserRepository(requireContext(), apiService) // Pass context here
        val factory = UserViewModelFactory(userRepository)

        // Initialize UserViewModel with ViewModelFactory
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        val percentageView: TextView = view.findViewById(R.id.percentage)
        val progresNumber: TextView = view.findViewById(R.id.progresNumber)
        val sugarTextView: TextView = view.findViewById(R.id.sugarNumber)
        val fatTextView: TextView = view.findViewById(R.id.fatNumber)
        val saltTextView: TextView = view.findViewById(R.id.saltNumber)
        val bmiTextView: TextView = view.findViewById(R.id.bmiNumber)
        val adviceTextView: TextView = view.findViewById(R.id.adviceTextView)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val progressBar5: ProgressBar = view.findViewById(R.id.progressBar5)
        val historybtn:CardView=view.findViewById(R.id.card_icon_button)
        historybtn.setOnClickListener{
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }
        // Observe LiveData for dashboard data
        userViewModel.dashboardData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    val dashboardData = result.data
                    val data = dashboardData.data
                    val progress = data.progress
                    val percentage = progress.percentage
                    val caloriesCurrent = progress.calories.current
                    val caloriesGoal = progress.calories.goal

                    val nutrients = data.nutrients
                    val sugar = nutrients.sugar
                    val fat = nutrients.fat
                    val salt = nutrients.salt

                    val bmi = data.bmi
                    val advice = data.advice

                    percentageView.text = "$percentage%"
                    progresNumber.text = "$caloriesCurrent"
                    sugarTextView.text = "$sugar"
                    fatTextView.text = "$fat"
                    saltTextView.text = "$salt"
                    bmiTextView.text = "$bmi"
                    adviceTextView.text = advice

                    progressBar.visibility = View.GONE
                    progressBar5.max = caloriesGoal
                }
                is Result.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(context, "Error: ${result.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Call fetchDashboardData() without passing currentDate
        userViewModel.fetchDashboardData()
    }
}
