package com.example.gallary.viewmodel

import androidx.lifecycle.*
import com.example.gallary.model.Photo
import com.example.gallary.network.UnsplashApi
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class GalleryViewModel : ViewModel() {
    private val _galleryList = MutableLiveData<List<Photo>>()
    val galleryList: LiveData<List<Photo>>
        get() = _galleryList

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            try {
                _galleryList.value = UnsplashApi.retrofitService.getPhotosAsync(10, 20).await()
            } catch (e: IOException) {
                Timber.d("ERROR $e")
            }
        }
    }
}