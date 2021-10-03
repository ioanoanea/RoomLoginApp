package com.example.roomloginapp.ui.activities

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.roomloginapp.R
import com.example.roomloginapp.data.SharedPreferencesManager
import com.example.roomloginapp.ui.fragments.LoggedInFragment
import com.example.roomloginapp.ui.fragments.LoginFragment
import com.example.roomloginapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferencesManager = SharedPreferencesManager(this)

        supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment()).commit()

        // check status
        userViewModel.status().observe(this) { status ->
            if (status.AUTH_SUCCESS) {
                // set SharedPreferences registered flag true
                sharedPreferencesManager.setRegistered(true)
                // switch to home fragment
                supportFragmentManager.beginTransaction().replace(R.id.container, LoggedInFragment()).commit()
            } else {
                Toast.makeText(this, status.MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }
    }
}