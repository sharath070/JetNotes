package com.sharath070.jetnotes.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharath070.jetnotes.R
import com.sharath070.jetnotes.components.NoteButton
import com.sharath070.jetnotes.components.NoteInputText
import com.sharath070.jetnotes.model.Notes
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteScreen(
    notes: List<Notes>,
    onAddNote: (Notes) -> Unit,
    onRemoveNote: (Notes) -> Unit
) {

    val title = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    val currentDate = Date()
    val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm")
    val formattedDate = formatter.format(currentDate)

    Column {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = null
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFFDADFE3))
        )

        Column(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteInputText(
                    text = title.value,
                    label = "Title",
                    modifier = Modifier.padding(vertical = 10.dp)
                ) { newValue ->
                    title.value = newValue
                }
                NoteInputText(
                    text = description.value,
                    label = "Description",
                    modifier = Modifier.padding(vertical = 10.dp)
                ) { newValue ->
                    description.value = newValue
                }
                NoteButton(text = "Save", modifier = Modifier.padding(vertical = 10.dp)) {
                    if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                        onAddNote(
                            Notes(
                                title = title.value,
                                description = description.value,
                                entryDate = formattedDate
                            )
                        )
                        title.value = ""
                        description.value = ""
                        Toast.makeText(context, "Note added!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            Divider(modifier = Modifier.padding(10.dp))
            LazyColumn {
                items(notes) {note ->
                    NoteRow(
                        note = note,
                        onNoteClicked = { onRemoveNote(note) }
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Notes = Notes("1","iaugDF", "aSUHDGK", "24-01-2024 17:25"),
    onNoteClicked: (Notes) -> Unit = {}
){
    Card(
        modifier = modifier
            .padding(vertical = 7.dp, horizontal = 18.dp)
            .fillMaxWidth()
            .clickable {
                onNoteClicked(note)
            },
        shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp),
        colors = CardDefaults.cardColors(Color(0xFFDFE6EB)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = modifier.padding(horizontal = 40.dp, vertical = 10.dp)) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = note.entryDate,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Light
            )
        }
    }
}


