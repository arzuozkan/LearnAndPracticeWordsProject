package com.useryoo.learnandpracticewordsproject.data.database

import androidx.room.*

@Dao
interface WordsDao {
    @Insert
    suspend fun addWord(word:WordModel)

    @Update
    suspend fun updateWord(word:WordModel)

    @Delete
    suspend fun deleteWord(word: WordModel)

    @Query("SELECT * FROM words_table")
    suspend fun allWords():List<WordModel>

    @Query("SELECT * FROM words_table WHERE id==:key")
    suspend fun getWord(key:Int):WordModel?
}