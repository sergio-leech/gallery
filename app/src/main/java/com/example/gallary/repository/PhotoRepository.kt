package com.example.gallary.repository

import com.example.gallary.database.PhotoDatabase
import com.example.gallary.database.asDatabaseModel
import com.example.gallary.model.Photo
import com.example.gallary.model.asDomainModel
import com.example.gallary.model.toPhoto
import com.example.gallary.network.UnsplashApi

class PhotoRepository(val database: PhotoDatabase) {
    suspend fun getPhoto(id: String): Photo {
        return database.photoDatabaseDao.getPhoto(id).toPhoto()
    }

    suspend fun getAllPhotos(): List<Photo> {
        return database.photoDatabaseDao.getAll().asDomainModel()
    }

    suspend fun refreshPhotos() {
        database.photoDatabaseDao.clear()
        val photos = UnsplashApi.retrofitService.getPhotosAsync(1, 100).await()
        database.photoDatabaseDao.insertPhoto(photos.asDatabaseModel())
    }

    suspend fun getSearchList(query: String): List<Photo> {
        database.photoDatabaseDao.clear()
        val res = UnsplashApi.retrofitService.searchPhotosAsync(query, 1, 100).await()
        if (res.results.isNotEmpty()) {
            database.photoDatabaseDao.insertPhoto(res.results.asDatabaseModel())
        }
        return database.photoDatabaseDao.getAll().asDomainModel()
    }
}