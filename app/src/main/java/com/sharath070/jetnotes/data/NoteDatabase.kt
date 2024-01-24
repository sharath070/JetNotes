package com.sharath070.jetnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sharath070.jetnotes.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}