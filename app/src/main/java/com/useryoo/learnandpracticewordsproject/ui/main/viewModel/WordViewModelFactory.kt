package com.useryoo.learnandpracticewordsproject.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.useryoo.learnandpracticewordsproject.data.database.WordsDao

class WordViewModelFactory (
    private val dataSource:WordsDao,
    private val application: Application
    ):ViewModelProvider.Factory{
        @Suppress("unchecked_cast")
        override fun <T: ViewModel> create(modelClass: Class<T>): T{
            if(modelClass.isAssignableFrom(WordViewModel::class.java))
                return WordViewModel(dataSource,application) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }