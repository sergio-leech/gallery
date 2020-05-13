package com.example.gallary.database

import com.example.gallary.model.Urls

data class UrlsRoom(
    val thumb: String?,
    val small: String,
    val medium: String?,
    val regular: String?,
    val large: String?,
    val full: String?,
    val raw: String?
)

fun Urls.toUrlsRoom() = UrlsRoom(
    thumb = thumb,
    small = small,
    medium = medium,
    regular = regular,
    large = large,
    full = full,
    raw = raw
)