package com.example.gallary.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoRoom::class], version = 7, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract val photoDatabaseDao: PhotoDatabaseDao
}