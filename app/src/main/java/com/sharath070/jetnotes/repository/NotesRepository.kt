package com.sharath070.jetnotes.repository

import com.sharath070.jetnotes.data.NoteDatabaseDao
import com.sharath070.jetnotes.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotesRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note: Notes) = noteDatabaseDao.insertNote(note)
    suspend fun deleteNote(note: Notes) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    fun getAllNotes() =
        noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()

}