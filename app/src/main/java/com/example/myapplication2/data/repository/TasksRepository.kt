package com.example.myapplication2.data.repository


import com.example.myapplication2.model.Task

class TasksRepository {

    private val tasks = mutableListOf(
        Task(1, "Buy milk", "Go to the store"),
        Task(2, "Workout", "Gym at 7 PM")
    )

    fun getTasks(): List<Task> = tasks

    fun getTaskById(id: Int): Task? =
        tasks.find { it.id == id }

    fun updateTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
        }
    }
}