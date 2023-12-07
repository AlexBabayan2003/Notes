package com.example.notes.mynotesapplication.ui.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.mynotesapplication.data.entity.Note
import com.example.notes.mynotesapplication.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {
    private val noteFlow = MutableStateFlow<Note?>(null)
    val note: StateFlow<Note?> = noteFlow

    fun getNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.getNote(noteId)?.let {
                noteFlow.emit(it)
            }
        }
    }

    fun deleteNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val note = noteFlow.value ?: return@launch
            notesRepository.deleteNote(note)
        }
    }

    fun saveOrUpdateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingNote = noteFlow.value
            val noteToUpdate = existingNote?.run { note.copy(id = id) } ?: note
            notesRepository.insertOrUpdateNote(noteToUpdate)
        }
    }
}