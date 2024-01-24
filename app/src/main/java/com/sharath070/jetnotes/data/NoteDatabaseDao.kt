package com.sharath070.jetnotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.sharath070.jetnotes.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from note_tbl")
    fun getNotes(): Flow<List<Notes>>

    @Query("SELECT * from note_tbl where id = :id")
    suspend fun getNoteById(id: String): Notes

    @Upsert
    suspend fun insertNote(note: Notes)

    @Query("DELETE from note_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Notes)

}
