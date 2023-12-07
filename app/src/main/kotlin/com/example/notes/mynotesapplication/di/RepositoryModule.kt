package com.example.notes.mynotesapplication.di

import com.example.notes.mynotesapplication.repo.GetAllNotesRepository
import com.example.notes.mynotesapplication.repo.GetAllNotesRepositoryImpl
import com.example.notes.mynotesapplication.repo.NotesRepository
import com.example.notes.mynotesapplication.repo.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNotesRepository(
        repo: NotesRepositoryImpl
    ): NotesRepository

    @Binds
    abstract fun bindAllNotesRepository(
        repo: GetAllNotesRepositoryImpl
    ): GetAllNotesRepository

}