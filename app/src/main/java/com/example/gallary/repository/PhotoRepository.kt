package com.example.gallary.repository

import com.example.gallary.database.PhotoDatabaseDao
import com.example.gallary.database.asDatabaseModel
import com.example.gallary.model.Photo
import com.example.gallary.model.asDomainModel
import com.example.gallary.model.toPhoto
import com.example.gallary.network.UnsplashService
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val databaseDao: PhotoDatabaseDao,
    private val unsplashService: UnsplashService
) {
    suspend fun getPhoto(id: String): Photo {
        return databaseDao.getPhoto(id).toPhoto()
    }

    suspend fun getAllPhotos(): List<Photo> {
        return databaseDao.getAll().asDomainModel()
    }

    suspend fun refreshPhotos() {
        databaseDao.clear()
        val photos = unsplashService.getPhotosAsync(1, 100).await()
        return databaseDao.insertPhoto(photos.asDatabaseModel())
    }

    suspend fun getSearchList(query: String): List<Photo> {
        databaseDao.clear()
        val res = unsplashService.searchPhotosAsync(query, 1, 100).await()
        if (res.results.isNotEmpty()) {
            databaseDao.insertPhoto(res.results.asDatabaseModel())
        }
        return databaseDao.getAll().asDomainModel()
    }
}