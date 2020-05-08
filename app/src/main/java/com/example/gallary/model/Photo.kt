package com.example.gallary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val created_at: String,
    val description: String,
    val urls: Urls,
    val user: User
) : Parcelable