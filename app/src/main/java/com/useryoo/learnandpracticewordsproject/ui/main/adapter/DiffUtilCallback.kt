package com.useryoo.learnandpracticewordsproject.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.useryoo.learnandpracticewordsproject.data.database.WordModel

class DiffUtilCallback:DiffUtil.ItemCallback<WordModel>() {
    override fun areItemsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
        return oldItem == newItem
    }
}
