package com.example.notes.mynotesapplication.repo

import androidx.paging.PagingSource
import com.example.notes.mynotesapplication.data.db.NotesDao
import com.example.notes.mynotesapplication.data.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : GetAllNotesRepository {

    override fun getAllNotes(): PagingSource<Int, Note> =
        notesDao.getAllNotes()
}