package com.example.gallary.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.gallary.database.PhotoDatabase
import com.example.gallary.repository.PhotoRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.example.gallary.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val repository = PhotoRepository(PhotoDatabase.getInstance(applicationContext))
        try {
            repository.refreshPhotos()
            Timber.d("WorkManager: Work request for sync is run")
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }
}