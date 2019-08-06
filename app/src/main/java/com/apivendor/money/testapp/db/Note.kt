package com.apivendor.money.testapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(

    //@ColumnInfo(name = "note_title")
    val title : String,
    val note : String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}