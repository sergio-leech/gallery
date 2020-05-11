package com.example.gallary.model

import androidx.lifecycle.LiveData


data class SearchPhoto(
    val total: Int,
    val total_pages: Int,
    val results: List<Photo>
)