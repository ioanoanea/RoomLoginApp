package com.example.roomloginapp.data.repository

import android.app.Application
import com.example.roomloginapp.data.database.AppDatabase
import com.example.roomloginapp.data.model.User
import com.example.roomloginapp.data.model.UserDao
import javax.inject.Inject

/**
 * Repository for [User] class
 */
class UserRepository @Inject constructor(private val application: Application) {

    private val userDao: UserDao

    init {
        val db = AppDatabase.getDatabase(application)
        userDao = db.userDao()
    }

    // Get user
    suspend fun fetchUser() = userDao.get()

    // add user
    suspend fun addUser(user: User) = userDao.insert(user)

}