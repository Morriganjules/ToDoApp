package com.example.todoapp.ROOM

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.TaskItem

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
abstract class TaskItemDatabase : RoomDatabase() {
    abstract fun taskItemDao(): TaskItemDao

    companion object {
        @Volatile
        private var INSTANCE: TaskItemDatabase? = null

        fun getDataBase(context: Context): TaskItemDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskItemDatabase::class.java,
                        "Task_item_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
