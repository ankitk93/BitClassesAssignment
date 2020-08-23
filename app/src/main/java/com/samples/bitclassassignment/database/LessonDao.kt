package com.samples.bitclassassignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */

@Dao
interface LessonDao {

    @Query("select * from lesson_table")
    fun getLesson(): LiveData<List<DatabaseLesson>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lesson: DatabaseLesson)
}