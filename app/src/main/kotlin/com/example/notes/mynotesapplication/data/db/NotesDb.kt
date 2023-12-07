package com.example.notes.mynotesapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.mynotesapplication.data.entity.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NotesDb : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

}