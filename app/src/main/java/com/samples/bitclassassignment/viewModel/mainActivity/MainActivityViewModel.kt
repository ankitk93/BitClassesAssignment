package com.samples.bitclassassignment.viewModel.mainActivity

import android.app.Application
import androidx.lifecycle.ViewModel
import com.samples.bitclassassignment.database.LessonDatabase.Companion.getDatabase
import com.samples.bitclassassignment.repository.LessonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */

class MainActivityViewModel(application: Application): ViewModel(){

    private val repository = LessonRepository(getDatabase(application).lessonDao())

    val lessonList = repository.lessons

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        getDataFromRepository()
    }

    private fun getDataFromRepository(){
        viewModelScope.launch {
            try {
                repository.refreshLesson()
            }catch (exception: Exception){
                exception.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}