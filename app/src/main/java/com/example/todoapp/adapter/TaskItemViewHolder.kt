package com.example.todoapp.adapter

import android.content.Context
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.TaskItem
import com.example.todoapp.TaskItemClickListener
import com.example.todoapp.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
) : RecyclerView.ViewHolder(binding.root){


    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    fun bindTaskItem(taskItem: TaskItem){
        binding.name.text = taskItem.name

        if(taskItem.isCompleted()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(context))

        binding.completeButton.setOnClickListener {
            clickListener.completTasktItem(taskItem)
        }

        binding.taskCellContainer.setOnClickListener {
            clickListener.editTasktItem(taskItem)
        }

        if(taskItem.dueTime() != null){
            binding.dueTime.text = timeFormat.format(taskItem.dueTime())
        } else {
            binding.dueTime.text = ""
        }
    }
}