package com.example.notes.mynotesapplication.ui.notes

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.mynotesapplication.data.entity.Note

class NotesDiffUtilItemCallback: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem == newItem
}