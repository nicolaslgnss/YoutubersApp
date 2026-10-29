package com.example.youtubersapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [YoutuberEntity::class], version = 1, exportSchema = false)
abstract class YoutuberDatabase : RoomDatabase() {

    abstract fun youtuberDao(): YoutuberDao

    companion object {
        @Volatile
        private var INSTANCE: YoutuberDatabase? = null

        fun getDatabase(context: Context): YoutuberDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YoutuberDatabase::class.java,
                    "youtuber_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}