package com.samples.bitclassassignment.network

import com.samples.bitclassassignment.database.DatabaseLesson
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */

interface ApiService{

    @GET("lesson")
    fun getLessons(): Deferred<List<DatabaseLesson>>
}