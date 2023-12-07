package com.example.notes.mynotesapplication.di

import android.content.Context
import androidx.room.Room
import com.example.notes.mynotesapplication.data.db.NotesDao
import com.example.notes.mynotesapplication.data.db.NotesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NotesDb =
        Room.databaseBuilder(
            context, NotesDb::class.java, "notes"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideNotesDao(db: NotesDb): NotesDao =
        db.getNotesDao()
}