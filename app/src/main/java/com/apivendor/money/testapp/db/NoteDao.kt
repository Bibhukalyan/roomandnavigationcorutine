package com.apivendor.money.testapp.db

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insertSingleNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun getNotes() : List<Note>

    @Insert
    suspend fun insertAllNote(vararg note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}