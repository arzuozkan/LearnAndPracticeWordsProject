package com.useryoo.learnandpracticewordsproject.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.useryoo.learnandpracticewordsproject.data.database.WordsDao
import com.useryoo.learnandpracticewordsproject.data.repository.WordRepository

class WordViewModelFactory (
    private val repository: WordRepository,
    private val application: Application
    ):ViewModelProvider.Factory{
        @Suppress("unchecked_cast")
        override fun <T: ViewModel> create(modelClass: Class<T>): T{
            if(modelClass.isAssignableFrom(WordViewModel::class.java))
                return WordViewModel(repository,application) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }