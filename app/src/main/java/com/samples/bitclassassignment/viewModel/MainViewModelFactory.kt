package com.samples.bitclassassignment.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */

class MainViewModelFactory(val application: Application): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(application) as T
        }
        throw IllegalArgumentException("No View Model found.")
    }
}