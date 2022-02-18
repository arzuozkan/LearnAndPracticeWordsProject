package com.useryoo.learnandpracticewordsproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "words_table")
data class WordModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int=0,

    @ColumnInfo(name = "word")
    var word:String,

    @ColumnInfo(name = "_word_definition")
    var wordDef:String,

    @ColumnInfo(name="word_example")
    var wordExample:String
):Serializable