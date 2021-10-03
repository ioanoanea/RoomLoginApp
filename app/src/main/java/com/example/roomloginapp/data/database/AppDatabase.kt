package com.example.roomloginapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomloginapp.data.model.User
import com.example.roomloginapp.data.model.UserDao

/**
 * Room database
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // Get user access object
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        // Create and pre-populate the database
        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "OptDb").allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build()
                this.instance = instance

                instance
            }
        }
    }
}