package com.example.gallary.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.gallary.model.Photo
import com.example.gallary.repository.PhotoRepository
import kotlinx.coroutines.launch

class FullPhotoViewModel @ViewModelInject constructor(
    private val repository: PhotoRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _photo = MutableLiveData<Photo>()
    val photo: LiveData<Photo>
        get() = _photo

    fun getPhoto(id: String) {
        viewModelScope.launch {
            _photo.value = repository.getPhoto(id)
        }
    }
}