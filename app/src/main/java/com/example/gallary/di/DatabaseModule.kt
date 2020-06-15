package com.example.gallary.di

import android.content.Context
import androidx.room.Room
import com.example.gallary.database.PhotoDatabase
import com.example.gallary.database.PhotoDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun providePhotoDatabase(@ApplicationContext context: Context): PhotoDatabase {
        return synchronized(this) {
            Room.databaseBuilder(
                context,
                PhotoDatabase::class.java,
                "photo_database"
            ).fallbackToDestructiveMigration().build()
        }
    }

    @Provides
    fun providePhotoDatabaseDao(photoDatabase: PhotoDatabase): PhotoDatabaseDao {
        return photoDatabase.photoDatabaseDao
    }
}
