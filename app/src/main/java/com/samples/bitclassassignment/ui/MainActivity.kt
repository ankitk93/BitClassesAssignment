package com.samples.bitclassassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.samples.bitclassassignment.R
import com.samples.bitclassassignment.adapter.LessonAdapter
import com.samples.bitclassassignment.databinding.ActivityMainBinding
import com.samples.bitclassassignment.viewModel.MainActivityViewModel
import com.samples.bitclassassignment.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private var mAdapter: LessonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModelFactory = MainViewModelFactory(application)
        val viewModel: MainActivityViewModel by lazy {
            ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        mAdapter = LessonAdapter()

        viewModel.lessonList.observe(this, Observer {lesson ->
            lesson?.apply {
                mAdapter?.lessons = lesson
            }
        })

        binding.rvLessonList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }
}
