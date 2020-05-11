package com.example.gallary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhotoRoom::class], version = 5)
abstract class PhotoDatabase : RoomDatabase() {
    abstract val photoDatabaseDao: PhotoDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PhotoDatabase? = null

        fun getInstance(context: Context): PhotoDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PhotoDatabase::class.java,
                        "photo_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}