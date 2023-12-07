package com.example.notes.mynotesapplication.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.notes.mynotesapplication.repo.GetAllNotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesRepository: GetAllNotesRepository
): ViewModel() {
    private val pager = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { getAllNotesRepository.getAllNotes() }
    )
    val allNotes = pager.flow
}