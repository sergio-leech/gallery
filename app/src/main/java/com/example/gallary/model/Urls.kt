package com.example.gallary.model

import android.os.Parcelable
import com.example.gallary.database.UrlsRoom
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Urls(
    val thumb: String?,
    val small: String,
    val medium: String?,
    val regular: String?,
    val large: String?,
    val full: String?,
    val raw: String?
) : Parcelable

fun UrlsRoom.toUrls() = Urls(
    thumb = thumb,
    small = small,
    medium = medium,
    regular = regular,
    large = large,
    full = full,
    raw = raw
)