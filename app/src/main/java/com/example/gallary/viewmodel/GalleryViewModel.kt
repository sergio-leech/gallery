package com.example.gallary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.gallary.database.PhotoDatabase
import com.example.gallary.model.Photo
import com.example.gallary.repository.PhotoRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository =
        PhotoRepository(PhotoDatabase.getInstance(application.applicationContext))
    private val imageList = ArrayList<Photo>()

    private val _galleryList = MutableLiveData<List<Photo>>()
    val galleryList: LiveData<List<Photo>>
        get() = _galleryList

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            try {
                repository.refreshPhotos()
                imageList.addAll(repository.getAllPhotos())
                _galleryList.value = imageList
            } catch (e: IOException) {
                Timber.d("ERROR $e")
            }
        }
    }

    fun getSearchList(query: String) {
        viewModelScope.launch {
            try {
                imageList.clear()
                imageList.addAll(repository.getSearchList(query))
                _galleryList.value = imageList
            } catch (e: IOException) {
                Timber.d("ERROR $e")
            }
        }
    }
}