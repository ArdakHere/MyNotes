 package com.example.mynotes

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

         view.findViewById<EditText>(R.id.title_text).inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
         view.findViewById<EditText>(R.id.title_text).isSingleLine = true
         view.findViewById<EditText>(R.id.title_text).imeOptions = android.view.inputmethod.EditorInfo.IME_ACTION_DONE


         val contentText = view.findViewById<EditText>(R.id.note_text).text
         val titleText = view.findViewById<EditText>(R.id.title_text).text
         val addButton: Button = view.findViewById<Button>(R.id.ADD)


         addButton.setOnClickListener {
             noteList.add(Note(titleText.toString(), contentText.toString()) )
             val postContentText = view.findViewById<EditText>(R.id.note_text).setText("")
             val postTitleText = view.findViewById<EditText>(R.id.title_text).setText("")
             adapter.notifyItemInserted(noteList.size)
             hideKeyboard()

             adapter.notifyDataSetChanged()

         }

     }

     private fun hideKeyboard() {
         val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
         imm.hideSoftInputFromWindow((view?.findViewById<EditText>(R.id.note_text))?.windowToken, 0)
     }
 }