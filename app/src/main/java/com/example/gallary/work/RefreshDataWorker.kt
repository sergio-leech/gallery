package com.example.gallary.work

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.gallary.repository.PhotoRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker @WorkerInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    val repository: PhotoRepository
) :
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.example.gallary.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        try {
            repository.refreshPhotos()
            Timber.d("WorkManager: Work request for sync is run")
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }
}