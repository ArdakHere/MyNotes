package com.example.mynotes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView.RecyclerListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settingsButton = findViewById<ImageButton>(R.id.settings_button)

        settingsButton.setOnClickListener{
            Intent(this, SecondActivity::class.java).also{
                startActivity(it)
            }
        }


        var noteList = mutableListOf<Note>(Note("Fefe", "frfr"))

        val addNoteButton = findViewById<FloatingActionButton>(R.id.add_note_button)

        val adapter = NoteAdapter(noteList)
        val myRecyclerView = findViewById<RecyclerView>(R.id.rvNotes)

        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        addNoteButton.setOnClickListener {
            val textTitle = findViewById<TextView>(R.id.titleTextView).text.toString()
            val textContent = findViewById<TextView>(R.id.contentTextView).text.toString()
            val taskToAdd = Note(textTitle, textContent)
            noteList.add(taskToAdd)
            adapter.notifyItemInserted(noteList.size)
        }
    }

}