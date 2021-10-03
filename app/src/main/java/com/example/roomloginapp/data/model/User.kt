package com.example.roomloginapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * User class
 */
@Entity
data class User(
    @PrimaryKey val id: Int = 1,
    @ColumnInfo val username: String,
    @ColumnInfo val password: String
)