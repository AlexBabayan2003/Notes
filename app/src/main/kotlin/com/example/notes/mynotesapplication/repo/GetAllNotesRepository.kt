package com.example.notes.mynotesapplication.repo

import androidx.paging.PagingSource
import com.example.notes.mynotesapplication.data.entity.Note
import kotlinx.coroutines.flow.Flow

interface GetAllNotesRepository {
    fun getAllNotes(): PagingSource<Int, Note>
}