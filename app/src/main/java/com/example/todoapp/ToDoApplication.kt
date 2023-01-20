package com.example.todoapp

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.todoapp.ROOM.TaskItemDatabase
import com.example.todoapp.ROOM.TaskItemRepository

@RequiresApi(Build.VERSION_CODES.O)
class ToDoApplication: Application() {
    private val database by lazy { TaskItemDatabase.getDataBase(this) }
    val repository by lazy { TaskItemRepository(database.taskItemDao()) }
}