package com.sharath070.jetnotes.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharath070.jetnotes.model.Notes
import com.sharath070.jetnotes.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Notes>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { notesList ->
                _noteList.value = notesList
            }
        }
    }


    fun addNote(note: Notes) =
        viewModelScope.launch { repository.addNote(note) }

    fun removeNote(note: Notes) =
        viewModelScope.launch { repository.deleteNote(note) }

}