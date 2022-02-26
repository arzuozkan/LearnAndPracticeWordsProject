package com.useryoo.learnandpracticewordsproject.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {

    @Query("SELECT * FROM words_table")
     fun allWords(): Flow<List<WordModel>>

    @Insert
    suspend fun addWord(word:WordModel)

    @Delete
    suspend fun deleteWord(word: WordModel)

    @Query("SELECT * FROM words_table WHERE id==:key")
    suspend fun getWord(key:Int):WordModel?
}