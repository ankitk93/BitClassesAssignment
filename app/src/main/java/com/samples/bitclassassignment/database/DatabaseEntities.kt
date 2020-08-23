package com.samples.bitclassassignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samples.bitclassassignment.domain.LessonDetails

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */
@Entity(tableName = "lesson_table")
data class DatabaseLesson(
    @PrimaryKey @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "createdAt")val createdAt : String,
    @ColumnInfo(name = "code")val code: String,
    @ColumnInfo(name = "heading")val heading: String,
    @ColumnInfo(name = "start_ime") val startTime : Int,
    @ColumnInfo(name = "end_time")val endTime : Int
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