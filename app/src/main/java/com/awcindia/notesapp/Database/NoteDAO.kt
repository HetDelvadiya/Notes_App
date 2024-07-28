package com.awcindia.notesapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.awcindia.notesapp.Model.Note

@Dao
interface NoteDAO {

    @Query("SELECT * FROM Notes ORDER BY id ASC")
    fun getNotes() : LiveData<List<Note>>

    @Insert
    suspend fun insertNote(note : Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}