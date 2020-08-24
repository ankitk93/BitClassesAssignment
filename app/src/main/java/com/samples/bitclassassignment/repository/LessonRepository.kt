package com.samples.bitclassassignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.samples.bitclassassignment.database.DatabaseLesson
import com.samples.bitclassassignment.database.LessonDao
import com.samples.bitclassassignment.database.asDomainModel
import com.samples.bitclassassignment.domain.LessonDetails
import com.samples.bitclassassignment.network.RetrofitClient

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */
class LessonRepository(private val  dao: LessonDao ){

    suspend fun refreshLesson(){
        val lesson = RetrofitClient.apiService.getLessons().await()
        dao.insert(lesson)
    }

    val lessons:LiveData<List<LessonDetails>> = Transformations.map(dao.getLesson()){
        it.asDomainModel()
    }
}