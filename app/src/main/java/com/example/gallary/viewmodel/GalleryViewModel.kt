package com.example.gallary.viewmodel

import androidx.lifecycle.*
import com.example.gallary.model.Photo
import com.example.gallary.network.UnsplashApi
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class GalleryViewModel : ViewModel() {
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
                imageList.addAll(UnsplashApi.retrofitService.getPhotosAsync(1, 100).await())
                _galleryList.value = imageList
            } catch (e: IOException) {
                Timber.d("ERROR $e")
            }
        }
    }

    fun getSearchList(query:String){
        viewModelScope.launch {
            try {
                val res=UnsplashApi.retrofitService.searchPhotosAsync(query,1,100).await()
                imageList.clear()
                imageList.addAll(res.results)
                _galleryList.postValue(imageList)
            }catch (e:IOException){
                Timber.d("ERROR $e")
            }
        }

    }
}