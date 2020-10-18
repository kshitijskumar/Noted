package com.example.noteto_do.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoClass::class, NoteClass::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val toDoDao: ToDoDao
    abstract val noteDao: NoteDao

    companion object{
        @Volatile
        private var instance: AppDatabase?= null

        fun getInstance(context: Context) : AppDatabase{
            synchronized(this){
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).fallbackToDestructiveMigrationFrom()
                        .build()

                }

                return instance as AppDatabase
            }
        }
    }
}