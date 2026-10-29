package com.example.youtubersapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "youtubers_table")
data class YoutuberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val imageUrl: String,
    val subscribers: String
)