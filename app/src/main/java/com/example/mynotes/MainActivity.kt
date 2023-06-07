package com.example.mynotes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val noteList = mutableListOf<Note>()
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settingsButton = findViewById<ImageButton>(R.id.settings_button)
        val addNoteButton = findViewById<FloatingActionButton>(R.id.add_note_button)

        settingsButton.setOnClickListener {
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }


        val adapter = NoteAdapter(noteList)
        val myRecyclerView = findViewById<RecyclerView>(R.id.rvNotes)

        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        noteList.add(Note("frefre", "efrfe"))

        val addFragment = fragment_add()

        addNoteButton.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.add_fragment, addFragment)
                addFragment.setSharedDataList(noteList, adapter)
                addToBackStack(null)
                commit()


            }
        }

    }
    private fun openNoteFragment() {
        // Create an instance of the NoteFragment
        val noteFragment = fragment_add()

        // Get the FragmentManager and start a FragmentTransaction
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Replace the content of the container with the NoteFragment
        fragmentTransaction.replace(R.id.add_fragment, noteFragment)

        // Add the transaction to the back stack (optional)
        fragmentTransaction.addToBackStack(null)

        // Commit the transaction
        fragmentTransaction.commit()
    }

}
