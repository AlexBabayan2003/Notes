package com.example.notes.mynotesapplication.repo

import com.example.notes.mynotesapplication.data.entity.Note


interface NotesRepository {
    suspend fun insertOrUpdateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNote(id: Int): Note?

}