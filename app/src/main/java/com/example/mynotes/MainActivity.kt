package com.example.mynotes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settingsButton = findViewById<ImageButton>(R.id.settings_button)
        val addNoteButton = findViewById<FloatingActionButton>(R.id.add_note_button)

        settingsButton.setOnClickListener{
            Intent(this, SettingsActivity::class.java).also{
                startActivity(it)
            }
        }

        var noteList = mutableListOf<Note>()

        val adapter = NoteAdapter(noteList)
        val myRecyclerView = findViewById<RecyclerView>(R.id.rvNotes)

        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        addNoteButton.setOnClickListener {
            Intent(this, AddNoteActivity::class.java).also{
                AdapterHolder.myAdapter = adapter
                val intent = Intent(this, AddNoteActivity::class.java)
                intent.putExtra("myList", ArrayList(noteList))

                intent.putExtra("Title Text", R.id.titleTextView)
                intent.putExtra("Content Text", R.id.contentTextView)
                startActivity(it)

            }
        }






    }

}
