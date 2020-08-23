package com.samples.bitclassassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samples.bitclassassignment.R
import com.samples.bitclassassignment.databinding.LessonSingleItemBinding
import com.samples.bitclassassignment.domain.LessonDetails

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */
class LessonAdapter(): RecyclerView.Adapter<LessonAdapter.ViewHolder>() {

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
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

    class ViewHolder(val viewDataBinding: LessonSingleItemBinding): RecyclerView.ViewHolder(viewDataBinding.root)
}