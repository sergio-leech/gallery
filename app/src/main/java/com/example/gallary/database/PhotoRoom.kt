package com.example.gallary.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gallary.model.Photo

@Entity(tableName = "photos")
data class PhotoRoom(
    @PrimaryKey
    val id: String,
    val created_at: String?,
    val description: String?,
    @Embedded
    val urls: UrlsRoom
)

fun List<Photo>.asDatabaseModel(): List<PhotoRoom> {
    return map {
        PhotoRoom(
            id = it.id,
            created_at = it.created_at,
            description = it.description,
            urls = it.urls.toUrlsRoom()
        )
    }
}