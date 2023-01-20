package com.example.todoapp.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.ROOM.TaskItemRepository
import com.example.todoapp.TaskItem
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
@RequiresApi(Build.VERSION_CODES.O)
class TaskViewModel(private val repository: TaskItemRepository): ViewModel() {

    var taskItems: LiveData<List<TaskItem>> = repository.allTaskItem.asLiveData()

    fun addTaskItem(newTask: TaskItem) = viewModelScope.launch {
            repository.insertTaskItem(newTask)
    }

    fun updateTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.updateTaskItem(taskItem)
    }


    fun setCompleted(taskItem: TaskItem) = viewModelScope.launch {
        if(!taskItem.isCompleted())
            taskItem.completedDate = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)
    }
}

class TaskItemModelFactory(private val repository: TaskItemRepository): ViewModelProvider.Factory{
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return  TaskViewModel(repository) as T

        throw  IllegalAccessException("Unknown Class for View Model")
    }


}
