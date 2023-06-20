package com.example.mynotes;

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import androidx.fragment.app.Fragment


class fragment_edit : Fragment(R.layout.fragment_edit){
    private lateinit var noteList: MutableList<Note>
    private lateinit var adapter: NoteAdapter
    private var index : Int = 0

    fun setSharedData(dataList: MutableList<Note>, adapter: NoteAdapter, noteIndex: Int) {
        this.index = noteIndex
        this.adapter = adapter
        noteList = dataList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the note data from arguments
        val title = arguments?.getString(ARG_NOTE_TITLE)
        val content = arguments?.getString(ARG_NOTE_CONTENT)
        val colorButton = view.findViewById<Button>(R.id.color_edit_button)

        // Populate the UI fields with the note's data for editing
        view.findViewById<EditText>(R.id.title_edit_text).setText(title)
        view.findViewById<EditText>(R.id.note_edit_text).setText(content)

        var colorTemp = noteList[index].color
        // Handle the save button click to save the edited note
        view.findViewById<Button>(R.id.SAVE).setOnClickListener {
            // Get the edited title and content from the UI fields
            val editedTitle = view.findViewById<EditText>(R.id.title_edit_text).text.toString()
            val editedContent = view.findViewById<EditText>(R.id.note_edit_text).text.toString()

            // TODO: Handle saving the edited note
            noteList.removeAt(index)
            noteList.add(index, Note(editedTitle, editedContent, colorTemp))
            adapter.notifyItemChanged(index)
            hideKeyboard()

            // Close the edit fragment
            requireActivity().supportFragmentManager.popBackStack()

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


    companion object {
        private const val ARG_NOTE_TITLE = "arg_note_title"
        private const val ARG_NOTE_CONTENT = "arg_note_content"

        fun newInstance(title: String?, content: String?): fragment_edit {
            val args = Bundle().apply {
                putString(ARG_NOTE_TITLE, title)
                putString(ARG_NOTE_CONTENT, content)
            }

            val fragment = fragment_edit()
            fragment.arguments = args

            return fragment
        }
    }
    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow((view?.findViewById<EditText>(R.id.note_text))?.windowToken, 0)
    }

}
