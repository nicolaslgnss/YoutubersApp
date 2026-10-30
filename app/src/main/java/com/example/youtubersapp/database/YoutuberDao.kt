package com.example.youtubersapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface YoutuberDao {

    @Query("SELECT * FROM youtubers_table")
    suspend fun getAllYoutubers() : List<YoutuberEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertYoutuber(youtuber: YoutuberEntity)

    @Delete
    suspend fun deleteYoutuber(youtuber: YoutuberEntity)
}