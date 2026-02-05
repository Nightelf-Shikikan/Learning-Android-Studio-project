package com.example.myapplication2.ui.taskedit

import androidx.lifecycle.ViewModel
import com.example.myapplication2.model.Task
import com.example.myapplication2.data.repository.TasksRepository

class TaskEditViewModel(
    private val repository: TasksRepository = TasksRepository()
) : ViewModel() {

    fun getTask(taskId: Int): Task? {
        return repository.getTaskById(taskId)
    }

    fun saveTask(task: Task) {
        repository.updateTask(task)
    }
}