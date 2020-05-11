package com.example.gallary.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhoto(photos: List<PhotoRoom>)

    @Query("SELECT*FROM photos WHERE id =:id")
    fun getPhoto(id: String): LiveData<PhotoRoom>

    @Query("SELECT * FROM photos")
    fun getAll(): LiveData<List<PhotoRoom>>

}