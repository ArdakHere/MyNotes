package com.example.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var notes : List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)

        init {
            itemView.setOnClickListener {
                val note = notes[adapterPosition]
                // Handle note click event
                // Open a window or perform any desired action
            }
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    // Bind data to ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val titleText = findViewById<TextView>(R.id.titleTextView)
            val contentText = findViewById<TextView>(R.id.contentTextView)
            titleText.text = notes[position].title
            contentText.text = notes[position].content
        }
    }

    // Return the number of notes
    override fun getItemCount(): Int {
        return notes.size
    }
}