package com.example.myapplication2.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.myapplication2.data.repository.DownloadRepository
import java.util.UUID
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: DownloadRepository
) : ViewModel() {

    fun downloadFile(url: String) {
        repository.enqueueDailyDownload(url)
    }
    fun observeDownload(workId: UUID, context: Context): LiveData<WorkInfo> {
        return WorkManager.getInstance(context).getWorkInfoByIdLiveData(workId)
    }
    fun startDailyDownload() {
        repository.enqueueDailyDownload("https://jsonplaceholder.typicode.com/posts/1")
    }
}