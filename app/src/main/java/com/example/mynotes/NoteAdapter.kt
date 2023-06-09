package com.example.mynotes

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var notes : MutableList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        init {
            itemView.setOnClickListener {
                val note = notes[absoluteAdapterPosition]
                // Handle note click event
                // Open a window or perform any desired action
            }
            itemView.setOnLongClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val taskToDelete = notes[position]

                    // Display a dialog or confirmation prompt for deletion
                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setTitle("Delete note")
                    builder.setMessage("Are you sure you want to delete this note?")

                    builder.setPositiveButton("Delete") { dialog, which ->
                        // Delete the task from your task list
                        notes.removeAt(position)
                        notifyItemChanged(position)
                    }

                    builder.setNegativeButton("Cancel", null)

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
                true
            }
        }

        fun bind(note: Note) {
            titleTextView.text = note.title
            contentTextView.text = note.content
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