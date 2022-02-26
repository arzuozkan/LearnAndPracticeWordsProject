package com.useryoo.learnandpracticewordsproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.useryoo.learnandpracticewordsproject.data.database.WordModel
import com.useryoo.learnandpracticewordsproject.databinding.WordListItemBinding

class WordsAdapter: ListAdapter<WordModel,WordsAdapter.WordListItem>(DiffUtilCallback()) {

        class WordListItem( val wordListItemBinding:WordListItemBinding)
            :RecyclerView.ViewHolder(wordListItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListItem {
        val layoutInflater=LayoutInflater.from(parent.context)
        val wordListItemBinding=WordListItemBinding.inflate(layoutInflater,parent,false)
        return WordListItem(wordListItemBinding)

    }
    override fun onBindViewHolder(holder: WordListItem, position: Int) {
        val word=getItem(position)
        holder.wordListItemBinding.apply {
            word.let {
                wordText.text= word.word
                wordMeaning.text=word.wordDef
            }
        }
    }
}