package com.example.mynotes

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val adapterReceived =  AdapterHolder.myAdapter
        val noteListReceived: MutableList<Note> = intent.getSerializableExtra("myList") as? MutableList<Note>
            ?: mutableListOf()

        val titleTextReceived = intent.getIntExtra("Title Text", 0).toString()
        val contentTextReceived = intent.getIntExtra("Content Text", 0).toString()


        val taskToAdd = Note(titleTextReceived,contentTextReceived)
        noteListReceived.add(noteListReceived.size,taskToAdd)
        adapterReceived.notifyItemInserted(noteListReceived.size)
        adapterReceived.notifyDataSetChanged()

    }
}