package com.example.gallary.model



data class SearchPhoto(
    val total: Int,
    val total_pages: Int,
    val results: List<Photo>
)