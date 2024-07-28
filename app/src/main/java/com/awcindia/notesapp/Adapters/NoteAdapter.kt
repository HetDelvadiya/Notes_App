package com.awcindia.notesapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.awcindia.notesapp.Model.Note
import com.awcindia.notesapp.databinding.NoteItemBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var noteList : List<Note> = ArrayList()
   private var listener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val noteView : Note = noteList[position]
        holder.bind(noteView)
    }

   inner class NoteViewHolder(val binding : NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(noteView: Note) {
            binding.title.text = noteView.title
            binding.discription.text = noteView.discription
            binding.card.setOnClickListener {
                Log.d("onClick" , "onClicked")
                listener?.onItemClick(noteView)
            }
        }
    }

    fun setNotes(note: List<Note>){
        noteList = note
        notifyDataSetChanged()
    }


    fun getNote(position: Int) : Note{
        return  noteList[position]
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
       this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }
}