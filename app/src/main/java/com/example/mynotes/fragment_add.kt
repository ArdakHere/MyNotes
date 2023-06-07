 package com.example.mynotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

 class fragment_add : Fragment(R.layout.fragment_add) {
    private lateinit var noteList: MutableList<Note>
    private lateinit var adapter: NoteAdapter

    // Create a setter method for the shared data source
    fun setSharedDataList(dataList: MutableList<Note>, adapter: NoteAdapter) {
        this.adapter = adapter
        noteList = dataList
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         val contentText = view.findViewById<EditText>(R.id.note_text).text
         val addButton: Button = view.findViewById<Button>(R.id.ADD)
         addButton.setOnClickListener {
             noteList.add(Note(contentText.toString(), contentText.toString()))
             // Notify the adapter about the new item added
             adapter.notifyItemInserted(noteList.size)
             adapter.notifyDataSetChanged()

         }
     }
}