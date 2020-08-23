package com.samples.bitclassassignment.domain


/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */
data class LessonDetails(val id : Int,
                          val createdAt : String,
                          val code: String,
                          val heading: String,
                          val startTime : Long,
                          val endTime : Long)