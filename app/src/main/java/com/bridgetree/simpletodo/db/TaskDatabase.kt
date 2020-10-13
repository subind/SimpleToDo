package com.bridgetree.simpletodo.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bridgetree.simpletodo.MyApplication

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {


    //Room will return the TaskDao, no need to explicitly provide implementation
    abstract fun getTaskDao(): TaskDao



    //Behaves like Singleton
    companion object {
        @Volatile //Keyword 'volatile' makes it easily visible to other threads
        var instance: TaskDatabase? = null

        fun getDatabase(): TaskDatabase? {
            if (instance == null) {
                synchronized(TaskDatabase) {//TODO : Check importance of synchronized keyword
                    instance =
                        MyApplication.context?.let {
                            Room.databaseBuilder(
                                it,
                                TaskDatabase::class.java,
                                "task_db.db"
                            ).build()
                        }
                        }

                }
            return instance
        }

    }



}