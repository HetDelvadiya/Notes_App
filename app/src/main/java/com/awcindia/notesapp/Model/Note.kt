package com.awcindia.notesapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(val title : String , val discription : String) {

    @PrimaryKey(autoGenerate = true)
    var id  = 0
}