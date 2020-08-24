package com.samples.bitclassassignment.viewModel.videoCallActivity

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * Created by ak93.droid@gmail.com on 24,August,2020
 */

class VideoCallViewModelFactory(val application: Application): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoCallViewModel::class.java)){
            return VideoCallViewModel(
                application
            ) as T
        }
        throw IllegalArgumentException("No View Model found.")
    }
}