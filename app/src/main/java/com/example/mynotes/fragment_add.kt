package com.example.mynotes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import com.google.android.material.floatingactionbutton.FloatingActionButton

class fragment_add : Fragment(R.layout.fragment_add) {
    private lateinit var noteList: MutableList<Note>
    private lateinit var adapter: NoteAdapter

    // Create a setter method for the shared data source
    fun setSharedDataList(dataList: MutableList<Note>, adapter: NoteAdapter) {
        this.adapter = adapter
        noteList = dataList
    }
    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<EditText>(R.id.title_text).inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        view.findViewById<EditText>(R.id.title_text).isSingleLine = true
        view.findViewById<EditText>(R.id.title_text).imeOptions = android.view.inputmethod.EditorInfo.IME_ACTION_DONE

        val addButton: Button = view.findViewById<Button>(R.id.ADD)

        val colorButton = view.findViewById<Button>(R.id.color_button)
        var colorTemp = Color.parseColor("#FFFFCC")   // Default note color

        addButton.setOnClickListener {
            val contentText = view.findViewById<EditText>(R.id.note_text).text.toString()
            val titleText = view.findViewById<EditText>(R.id.title_text).text.toString()

            noteList.add(Note(titleText, contentText,  colorTemp))
            val postContentText = view.findViewById<EditText>(R.id.note_text).setText("")
            val postTitleText = view.findViewById<EditText>(R.id.title_text).setText("")
            adapter.notifyItemInserted(noteList.size-1)
            hideKeyboard()
        }

        colorButton.setOnClickListener { view ->
            val popupMenu = PopupMenu(requireContext(), view)
            popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.color_red -> {
                        colorTemp = Color.parseColor("#F08888")
                        true
                    }
                    R.id.color_green -> {
                        colorTemp = Color.parseColor("#99FF99")
                        true
                    }
                    R.id.color_light_blue -> {
                        colorTemp = Color.parseColor("#66B2FF")
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }


    }


    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow((view?.findViewById<EditText>(R.id.note_text))?.windowToken, 0)
    }
}