package com.awcindia.notesapp.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.  ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.awcindia.notesapp.Adapters.NoteAdapter
import com.awcindia.notesapp.Application.NoteApplication
import com.awcindia.notesapp.Model.Note
import com.awcindia.notesapp.R
import com.awcindia.notesapp.ViewModel.MainFactory
import com.awcindia.notesapp.ViewModel.MainViewModel
import com.awcindia.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var resultContract: ActivityResultLauncher<Intent>
    private lateinit var updateResultContract: ActivityResultLauncher<Intent>

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = NoteAdapter()
        adapter.setOnItemClickListener(this)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = GridLayoutManager(this, 2)

        activityResultLauncher()
        updateResultLauncher()

        val repository = (application as NoteApplication).repository
        mainViewModel =
            ViewModelProvider(this, MainFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.getNotes().observe(this, Observer { note ->
            adapter.setNotes(note)
        })

        binding.add.setOnClickListener {
            val i = Intent(this, AddNotes::class.java)
            resultContract.launch(i)
        }


        ejnewhdyqahbdqj

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                mainViewModel.deleteNote(adapter.getNote(viewHolder.adapterPosition))
            }

        }).attachToRecyclerView(binding.rv)
    }

    private fun activityResultLauncher() {
        resultContract = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {

                if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                    val title: String = it.data!!.getStringExtra("title").toString()
                    val discription = it.data?.getStringExtra("description").toString()

                    val note = Note(title, discription)
                    mainViewModel.insertNote(note)

                }
            })
    }

    override fun onItemClick(note: Note) {
        Log.d("onClick", "item clicked from main ")
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra("titleUpdate", note.title)
        intent.putExtra("descriptionUpdate", note.discription)
        intent.putExtra("idUpdate" , note.id)
        updateResultContract.launch(intent)
    }

    private fun updateResultLauncher() {
        updateResultContract = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {

                if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                    val title: String = it.data!!.getStringExtra("title2").toString()
                    val discription = it.data?.getStringExtra("description2").toString()
                    val noteId : Int = it.data?.getIntExtra("id2" , -1) as Int
                    val newNote = Note(title, discription)
                    newNote.id = noteId
                    Log.d("update", "Update Note")
                    mainViewModel.updateNote(newNote)
                }
            })
    }
}