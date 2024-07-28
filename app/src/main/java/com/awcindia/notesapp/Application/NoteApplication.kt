package com.awcindia.notesapp.Application

import android.app.Application
import com.awcindia.notesapp.Database.NoteDatabase
import com.awcindia.notesapp.Repository.NoteRepository

class NoteApplication : Application() {

    lateinit var repository: NoteRepository
    override fun onCreate() {
        super.onCreate()
        initiale()
    }

    private fun initiale() {

        val noteDAO = NoteDatabase.getDatabase(this).getNoteDao()
        repository =  NoteRepository(noteDAO)
    }
}