package com.example.roomloginapp.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for [User] class
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY id DESC LIMIT 1")
    fun get(): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)
}