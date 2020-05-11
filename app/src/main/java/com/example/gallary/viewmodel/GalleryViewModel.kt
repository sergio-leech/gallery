package com.example.gallary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.room.RoomDatabase
import com.example.gallary.database.PhotoDatabase
import com.example.gallary.model.Photo
import com.example.gallary.network.UnsplashApi
import com.example.gallary.repository.PhotoRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PhotoRepository(PhotoDatabase.getInstance(application.applicationContext))
    private val imageList =ArrayList<Photo>()
    private val _galleryList = MutableLiveData<List<Photo>>()
   /* val galleryList: LiveData<List<Photo>>
        get() = _galleryList*/
   /*val galleryList: LiveData<List<Photo>>
       get() = repository.listPhotos*/
   val galleryList: LiveData<List<Photo>>
       get() = _galleryList

    init {
        _galleryList.value = repository.listPhotos.value?.toList()

        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            try {
                repository.refreshPhotos()

             //   _galleryList.value = repository.listPhotos.value

            } catch (e: IOException) {
                Timber.d("ERROR $e")
            }
        }
    }

    fun getSearchList(query:String){
        viewModelScope.launch {
            try {
                val res=UnsplashApi.retrofitService.searchPhotosAsync(query,1,100).await()

                /*imageList.clear()
                imageList.addAll(res.results)*/

                _galleryList.value = res.results
            }catch (e:IOException){
                Timber.d("ERROR $e")
            }
        }

    }
}