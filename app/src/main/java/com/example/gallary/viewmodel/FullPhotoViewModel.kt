package com.example.gallary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.gallary.database.PhotoDatabase
import com.example.gallary.model.Photo
import com.example.gallary.repository.PhotoRepository
import kotlinx.coroutines.launch

class FullPhotoViewModel(application: Application, _id: String) : AndroidViewModel(application) {
    private val repository = PhotoRepository(PhotoDatabase.getInstance(application))
    private val id = _id
    private val _photo = MutableLiveData<Photo>()
    val photo: LiveData<Photo>
        get() = _photo

    init {
        getPhoto()
    }

    private fun getPhoto() {
        viewModelScope.launch {
            _photo.value = repository.getPhoto(id)
        }
    }
}