package com.samples.bitclassassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */
@Database(entities = [DatabaseLesson::class], version = 1, exportSchema = false)
abstract class LessonDatabase: RoomDatabase(){
    abstract fun lessonDao(): LessonDao

    companion object{
        @Volatile
        private var INSTANCE: LessonDatabase? = null

        fun getDatabase(context: Context): LessonDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LessonDatabase::class.java,
                    "lesson_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}