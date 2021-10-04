package com.example.roomloginapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.roomloginapp.data.model.User
import com.example.roomloginapp.data.repository.UserRepository
import com.example.roomloginapp.data.status.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [User] class
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
) : AndroidViewModel( application ) {

    private var user: LiveData<User>? = null
    private val status: MutableLiveData<Status> by lazy {
        MutableLiveData<Status>()
    }

    init {
        // Get user data
        fetchUser()
    }

    private fun fetchUser() {
        // Get user data
        viewModelScope.launch {
            user = userRepository.fetchUser().asLiveData()
        }
    }

    // Add user to database
    fun signInUser(username: String, password: String) {
        viewModelScope.launch {

            if (username == "") {
                status.value = Status(AUTH_SUCCESS = false, MESSAGE = "Please enter username!")
            } else if (user?.value?.username == username) {
                if (user!!.value?.password == password) {
                    // Set user status to auth success
                    status.value = Status(AUTH_SUCCESS = true, MESSAGE = "Signed In Success!")
                } else {
                    // Set user status to auth failed
                    status.value = Status(AUTH_SUCCESS = false, MESSAGE = "Invalid Password!")
                }
            } else if(password.length < 6) {
                status.value = Status(AUTH_SUCCESS = false, MESSAGE = "Password is to short! (min 6 characters)")
            } else {
                // Create new user
                val newUser: User = User(username = username, password = password)
                // Add user to database
                userRepository.addUser(newUser)
                fetchUser()
                // set user status to auth success
                status.value = Status(AUTH_SUCCESS = true, MESSAGE = "Signed In Success!")
            }
        }
    }

    // Check if user is user is logged in
    fun status(): LiveData<Status> = status

    // Returns user
    fun user(): LiveData<User>? = user
}