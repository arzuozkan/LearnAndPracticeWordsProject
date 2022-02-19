package com.useryoo.learnandpracticewordsproject.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.useryoo.learnandpracticewordsproject.data.database.WordModel
import com.useryoo.learnandpracticewordsproject.data.database.WordsDao
import kotlinx.coroutines.launch

class WordViewModel(
    private val db:WordsDao,
    application: Application
) :AndroidViewModel(application){
    var wordsList=MutableLiveData<List<WordModel>>()

    init {
        getAllWords()
    }

    private fun getAllWords() {
        viewModelScope.launch {
            wordsList.value=db.allWords()
        }
    }

    fun wordAdd(word:WordModel){
        viewModelScope.launch {
            db.addWord(word)
        }
    }

    fun wordListUpdate(wordList:List<WordModel>){
        viewModelScope.launch {
            db.updateList(wordList)
        }
    }

    fun wordDelete(word:WordModel){
        viewModelScope.launch {
            db.deleteWord(word)
        }
    }

}