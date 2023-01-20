package com.example.todoapp

interface TaskItemClickListener {
    fun editTasktItem(taskItem: TaskItem)
    fun completTasktItem(taskItem: TaskItem)
}