package com.example.notes.mynotesapplication.repo

import com.example.notes.mynotesapplication.data.db.NotesDao
import com.example.notes.mynotesapplication.data.entity.Note
import javax.inject.Inject


class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : NotesRepository {

    override suspend fun insertOrUpdateNote(note: Note) {
        notesDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }

    override suspend fun getNote(id: Int): Note? =
        notesDao.getNote(id)

}