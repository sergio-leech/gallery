package com.example.gallary.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhoto(photos: List<PhotoRoom>)

    @Query("SELECT*FROM photos WHERE id =:id")
    suspend fun getPhoto(id: String): PhotoRoom

    @Query("SELECT * FROM photos")
    suspend fun getAll(): List<PhotoRoom>

    @Query("DELETE FROM photos")
    suspend fun clear()
}