package com.samples.bitclassassignment.network

import com.google.gson.annotations.SerializedName
import com.samples.bitclassassignment.database.DatabaseLesson

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */


/**
 * lesson list
 */
data class LessonsContainer(val lessons: List<NetworkLesson>)

/**
 * lesson network object
 */
data class NetworkLesson(
    @SerializedName("id") val id: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("code") val code: String,
    @SerializedName("heading") val heading: String,
    @SerializedName("start_time") val startTime: Int,
    @SerializedName("end_time") val endTime: Int
)

/**
 * Convert Network results to database objects
 */
fun LessonsContainer.asDatabaseModel() : List<DatabaseLesson>{
    return lessons.map {
        DatabaseLesson(id = it.id,
            createdAt = it.createdAt,
            code = it.code,
            heading = it.heading,
            startTime = it.startTime,
            endTime = it.endTime)
    }
}