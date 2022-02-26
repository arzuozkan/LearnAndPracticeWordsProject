package com.useryoo.learnandpracticewordsproject.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.useryoo.learnandpracticewordsproject.data.database.WordModel
import com.useryoo.learnandpracticewordsproject.data.database.WordsDao
import com.useryoo.learnandpracticewordsproject.data.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(
    private val repository: WordRepository,
    application: Application
) : AndroidViewModel(application){
    val allWords:LiveData<List<WordModel>> = repository.allWords.asLiveData()

    fun insert(word:WordModel){
        viewModelScope.launch {
            repository.insert(word)
        }
    }

}