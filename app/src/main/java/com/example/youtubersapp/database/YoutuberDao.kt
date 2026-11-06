package com.example.youtubersapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface YoutuberDao {

    @Query("SELECT * FROM youtubers_tabla")
    suspend fun obtenerTodos(): List<YoutuberEntity>

    @Insert
    suspend fun insertarYoutuber(youtuber: YoutuberEntity)
}