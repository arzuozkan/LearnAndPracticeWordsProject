package com.useryoo.learnandpracticewordsproject.data.repository

import androidx.annotation.WorkerThread
import com.useryoo.learnandpracticewordsproject.data.database.WordModel
import com.useryoo.learnandpracticewordsproject.data.database.WordsDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordsDao: WordsDao) {
    val allWords: Flow<List<WordModel>> = wordsDao.allWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word:WordModel){
        wordsDao.addWord(word)
    }
}