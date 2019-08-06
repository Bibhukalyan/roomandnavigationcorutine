package com.apivendor.money.testapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.apivendor.money.testapp.R
import com.apivendor.money.testapp.db.NoteDatabase
import com.apivendor.money.testapp.ui.adapter.NoteListAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_add_note.setOnClickListener{
            val action = HomeFragmentDirections.actionAddNote()
            Navigation.findNavController(it).navigate(action)
        }

        note_list_recycler.apply {
            setHasFixedSize(true)
            //layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
            layoutManager = StaggeredGridLayoutManager(3,RecyclerView.VERTICAL)
        }

        launch {

            context?.let {
                val noteList = NoteDatabase(it).getNoteDao().getNotes()

                note_list_recycler.adapter = NoteListAdapter(noteList)

            }

        }


    }

}
