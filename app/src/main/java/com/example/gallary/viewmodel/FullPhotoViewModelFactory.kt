package com.example.gallary.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class FullPhotoViewModelFactory(private val application: Application, private val id: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FullPhotoViewModel::class.java)) {
            return FullPhotoViewModel(application, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}