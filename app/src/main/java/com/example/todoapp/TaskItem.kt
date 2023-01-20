package com.example.todoapp

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID
@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "Task_item_table")
data class TaskItem(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "desc") var description: String,
    @ColumnInfo(name = "dueTimeString") var dueTime: String?,
    @ColumnInfo(name = "completedDateString") var completedDate: String?,
    @PrimaryKey(autoGenerate = true) var id: Int =0
){
    fun completedDate(): LocalDate? = if(completedDate == null) null else LocalDate.parse(
        completedDate, dateFormatter
    )
    fun dueTime(): LocalTime? = if (dueTime == null) null else LocalTime.parse(dueTime, timeFormatter)
    fun isCompleted() = completedDate != null
    fun imageResource(): Int = if (isCompleted()) R.drawable.baseline_check_circle_outline_24 else R.drawable.baseline_radio_button_unchecked_24
    fun imageColor(context: Context): Int = if (isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)

    companion object{
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}
