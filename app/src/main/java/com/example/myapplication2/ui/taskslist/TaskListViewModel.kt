package com.example.myapplication2.ui.taskslist



import androidx.lifecycle.ViewModel
import com.example.myapplication2.model.Task
import com.example.myapplication2.data.repository.TasksRepository

class TasksListViewModel(
    private val repository: TasksRepository = TasksRepository()
) : ViewModel() {

    val tasks: List<Task> = repository.getTasks()
}