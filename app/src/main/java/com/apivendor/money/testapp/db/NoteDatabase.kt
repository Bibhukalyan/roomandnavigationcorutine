package com.apivendor.money.testapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class],version = 1)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDao

    companion object{
        @Volatile private var instance : NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) : NoteDatabase{
            return instance ?: synchronized(LOCK){
                return instance ?: getNoteDataBase(context).also {
                    instance = it
                }
            }
        }

        private fun getNoteDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NoteDatabase::class.java,
                "notedb").build()
    }
}