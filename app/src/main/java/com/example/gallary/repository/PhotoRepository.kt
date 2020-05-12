package com.example.gallary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.gallary.database.PhotoDatabase
import com.example.gallary.database.asDatabaseModel
import com.example.gallary.model.Photo
import com.example.gallary.model.asDomainModel
import com.example.gallary.network.UnsplashApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRepository (val database:PhotoDatabase) {
  /*  val listPhotos = Transformations.map(database.photoDatabaseDao.getAll()){
        it.asDomainModel()
    }*/

  // val listPhotos = database.photoDatabaseDao.getAll().asDomainModel()

    suspend fun getAllPhotos():List<Photo>{
           return database.photoDatabaseDao.getAll().asDomainModel()
    }


    suspend fun refreshPhotos(){
            val photos = UnsplashApi.retrofitService.getPhotosAsync(1,100).await()
            database.photoDatabaseDao.insertPhoto(photos.asDatabaseModel())

    }
}