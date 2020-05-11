package com.example.gallary.model

import android.os.Parcelable
import com.example.gallary.database.PhotoRoom
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val created_at: String?,
    val description: String?,
    val urls: Urls
) : Parcelable

fun List<PhotoRoom>.asDomainModel():List<Photo>{
    return map {
        Photo(
            id = it.id,
            created_at = it.created_at,
            description = it.description,
            urls = it.urls.toUrls()
        )
    }
}