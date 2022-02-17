package com.useryoo.learnandpracticewordsproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.useryoo.learnandpracticewordsproject.databinding.WordListItemBinding

class WordRecyclerViewAdapter(private var wordList:ArrayList<String>)
    : RecyclerView.Adapter<WordRecyclerViewAdapter.WordListItem>() {

        class WordListItem( val wordListItemBinding:WordListItemBinding)
            :RecyclerView.ViewHolder(wordListItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListItem {
        val layoutInflater=LayoutInflater.from(parent.context)
        val wordListItemBinding=WordListItemBinding.inflate(layoutInflater,parent,false)
        return WordListItem(wordListItemBinding)

    }
    override fun onBindViewHolder(holder: WordListItem, position: Int) {
        val word=wordList[position]
        holder.wordListItemBinding.apply {
            wordText.text="Word"
            wordMeaning.text="Word meaning"

        }
    }
    override fun getItemCount()=wordList.size
}