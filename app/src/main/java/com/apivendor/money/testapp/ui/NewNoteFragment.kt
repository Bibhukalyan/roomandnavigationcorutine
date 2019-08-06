package com.apivendor.money.testapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.apivendor.money.testapp.R
import com.apivendor.money.testapp.db.Note
import com.apivendor.money.testapp.db.NoteDatabase
import com.apivendor.money.testapp.helper.toast
import kotlinx.android.synthetic.main.fragment_new_note.*
import kotlinx.coroutines.launch

class NewNoteFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_save_note.setOnClickListener{view ->
            val note_title = et_note_title.text.toString().trim()
            val note_text = et_note_text.text.toString().trim()

            if (note_title.isEmpty()){
                et_note_title.error = "Give valid title"
                et_note_title.requestFocus()
                return@setOnClickListener
            }

            if (note_text.isEmpty()){
                et_note_text.error = "Enter valid Note"
                et_note_text.requestFocus()
                return@setOnClickListener
            }

            /**calling kotlin coroutines*/
            launch {
                val note = Note(note_title,note_text)

                context?.let {
                    NoteDatabase(it).getNoteDao().insertSingleNote(note)
                    it.toast("Successfully inserted")
                    /**
                     * Navigate back to home fragment*/
                    val action = NewNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }



            }

        }
    }

    /*private fun saveNote(note: Note){

        class SaveNote : AsyncTask<Void,Void,Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                NoteDatabase(activity!!).getNoteDao().insertSingleNote(note)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity,"Inserted Successfully",Toast.LENGTH_SHORT).show()
            }

        }

        SaveNote().execute()
    }*/


}
