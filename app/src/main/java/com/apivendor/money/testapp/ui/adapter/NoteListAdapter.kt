package com.apivendor.money.testapp.ui.adapter

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.apivendor.money.testapp.R
import com.apivendor.money.testapp.db.Note
import com.apivendor.money.testapp.ui.HomeFragmentDirections
import com.apivendor.money.testapp.ui.NewNoteFragmentDirections
import kotlinx.android.synthetic.main.indiview_note_item.view.*

class NoteListAdapter(val noteList : List<Note>?) : RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.indiview_note_item,parent,false)
        return NoteListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList?.size ?: 0
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        //val note = noteList?.get(position)
        holder.itemView.tv_note_title.text = noteList!![position].title
        holder.itemView.tv_note_text.text = noteList[position].note

        holder.itemView.setOnClickListener{
            val action = HomeFragmentDirections.actionAddNote()
            action.note = noteList[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class NoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*val tv_note_title = itemView.findViewById<TextView>(R.id.tv_note_title)
        val tv_note_text = itemView.findViewById<TextView>(R.id.tv_note_text)
*/

    }
}