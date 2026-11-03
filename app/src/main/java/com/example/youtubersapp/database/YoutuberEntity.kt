package com.example.youtubersapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "youtubers_tabla")
data class YoutuberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val canalUrl: String,
    val subscribers: String
)