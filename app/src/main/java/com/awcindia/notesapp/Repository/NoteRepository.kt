package com.awcindia.notesapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.awcindia.notesapp.Database.NoteDAO
import com.awcindia.notesapp.Model.Note

class NoteRepository(private val noteDAO: NoteDAO) {

    private val noteLiveData = MutableLiveData<List<Note>>()

    val allNote : LiveData<List<Note>> = noteLiveData

     fun getNotes() : LiveData<List<Note>>{
        return noteDAO.getNotes()
    }

    suspend fun insertNote(note: Note){
        noteDAO.insertNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDAO.deleteNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDAO.updateNote(note)
    }

}