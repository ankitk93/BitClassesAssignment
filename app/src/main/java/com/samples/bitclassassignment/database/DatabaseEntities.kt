package com.samples.bitclassassignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.samples.bitclassassignment.domain.LessonDetails

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */
@Entity(tableName = "lesson_table")
data class DatabaseLesson(
    @SerializedName("id") @PrimaryKey @ColumnInfo(name = "id") val id : Int,
    @SerializedName("createdAt") @ColumnInfo(name = "createdAt")val createdAt : String,
    @SerializedName("code") @ColumnInfo(name = "code")val code: String,
    @SerializedName("heading") @ColumnInfo(name = "heading")val heading: String,
    @SerializedName("start_time") @ColumnInfo(name = "start_ime") val startTime : Long,
    @SerializedName("end_time") @ColumnInfo(name = "end_time")val endTime : Long
)

fun List<DatabaseLesson>.asDomainModel(): List<LessonDetails>{
    return map {
        LessonDetails(id = it.id,
            createdAt = it.createdAt,
            code = it.code,
            heading = it.heading,
            startTime = it.startTime,
            endTime = it.endTime)
    }
}