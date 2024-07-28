package com.awcindia.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.awcindia.notesapp.Model.Note

@Database(entities = [Note::class] , version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDAO

    companion object{
        @Volatile
        var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {

            if (INSTANCE == null) {

                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "nameDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}