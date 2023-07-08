package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Table created with table name in DB

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "text") var text : String) {
    @PrimaryKey(autoGenerate = true) var id = 0
    //we don't want to pass the id again so we made Primary key as autogenerate = true
}