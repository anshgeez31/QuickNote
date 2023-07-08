package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    //we are using suspend keyword (coroutines) so that these IO functions can be only be called from other suspend functions
    //or from background threads. Basically these insert and delete functions are IO based and they are very heavy so in order to
    //avoid lagging of app we are making sure that these functions don't get called from main thread.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(note : Note)

    @Delete
    fun delete(note: Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes() : LiveData<List<Note>> //LiveData will ensure that you can see all updated data Live on real time
}