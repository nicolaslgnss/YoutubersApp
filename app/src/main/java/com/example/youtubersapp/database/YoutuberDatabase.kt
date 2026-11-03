package com.example.youtubersapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.youtubersapp.database.YoutuberEntity

@Database(entities = [YoutuberEntity::class], version = 1)
abstract class YoutuberDatabase : RoomDatabase() {

    abstract fun youtuberDao(): YoutuberDao

    companion object {
        @Volatile
        private var INSTANCE: YoutuberDatabase? = null

        fun obtenerInstancia(context: Context): YoutuberDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YoutuberDatabase::class.java,
                    "youtubers_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}