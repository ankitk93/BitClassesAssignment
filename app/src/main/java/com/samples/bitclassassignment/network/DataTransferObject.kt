package com.samples.bitclassassignment.network

import com.google.gson.annotations.SerializedName
import com.samples.bitclassassignment.database.DatabaseLesson

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */


/**
 * lesson network object
 */
data class NetworkLesson(
    @SerializedName("id") val id: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("code") val code: String,
    @SerializedName("heading") val heading: String,
    @SerializedName("start_time") val startTime: Long,
    @SerializedName("end_time") val endTime: Long
)

fun List<NetworkLesson>.asDatabaseModel(): List<DatabaseLesson>{
    return map {
        DatabaseLesson(id = it.id,
            createdAt = it.createdAt,
            code = it.code,
            heading = it.heading,
            startTime = it.startTime,
            endTime = it.endTime)
    }
}