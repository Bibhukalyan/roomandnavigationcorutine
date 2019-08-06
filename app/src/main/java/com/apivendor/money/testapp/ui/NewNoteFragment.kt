package com.apivendor.money.testapp.ui


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation

import com.apivendor.money.testapp.R
import com.apivendor.money.testapp.db.Note
import com.apivendor.money.testapp.db.NoteDatabase
import com.apivendor.money.testapp.helper.toast
import kotlinx.android.synthetic.main.fragment_new_note.*
import kotlinx.coroutines.launch

class NewNoteFragment : BaseFragment() {

    private var note : Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            note = NewNoteFragmentArgs.fromBundle(it).note
            et_note_title.setText(note?.title)
            et_note_text.setText(note?.note)
        }

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
                val mNote = Note(note_title,note_text)

                context?.let {

                    if (note == null){
                        NoteDatabase(it).getNoteDao().insertSingleNote(mNote)
                        it.toast("Successfully inserted")
                    }else{
                        mNote.id = note!!.id
                        NoteDatabase(it).getNoteDao().updateNote(mNote)
                        it.toast("Successfully updated")
                    }

                    /**
                     * Navigate back to home fragment*/
                    val action = NewNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }



            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.delete_note ->
                if (note == null)
                    context?.toast("Not able to delete")
                else
                    deleteNote()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote(){
        AlertDialog.Builder(context).apply {
            setTitle("Delete Note?")
            setMessage("This action can't be undo")
            setPositiveButton("Yes"){_,_->
                launch {
                    NoteDatabase(context).getNoteDao().deleteNote(note!!)
                    val action = NewNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view!!).navigate(action)
                }
            }
            setNegativeButton("No"){_,_->

            }
        }.create().show()
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
