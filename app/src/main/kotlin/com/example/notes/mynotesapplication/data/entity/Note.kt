package com.example.notes.mynotesapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val content: String,
    val color: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)