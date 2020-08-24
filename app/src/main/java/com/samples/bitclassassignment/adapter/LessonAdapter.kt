package com.samples.bitclassassignment.adapter

import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samples.bitclassassignment.R
import com.samples.bitclassassignment.databinding.LessonSingleItemBinding
import com.samples.bitclassassignment.domain.LessonDetails
import com.samples.bitclassassignment.ui.VideoCallActivity

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */
class LessonAdapter(val application: Application): RecyclerView.Adapter<LessonAdapter.ViewHolder>() {

    var lessons:List<LessonDetails> = emptyList()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LessonSingleItemBinding>(LayoutInflater.from(parent.context),
            R.layout.lesson_single_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.lesson = lessons[position]
        }

        holder.viewDataBinding.btnJoin.setOnClickListener {
            application.startActivity(Intent(application, VideoCallActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

    class ViewHolder(val viewDataBinding: LessonSingleItemBinding): RecyclerView.ViewHolder(viewDataBinding.root)
}