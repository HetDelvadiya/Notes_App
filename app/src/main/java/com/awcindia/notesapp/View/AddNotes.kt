package com.awcindia.notesapp.View

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awcindia.notesapp.R
import com.awcindia.notesapp.databinding.ActivityAddNotesBinding
import com.awcindia.notesapp.databinding.ActivityMainBinding

class AddNotes : AppCompatActivity() {

    lateinit var binding: ActivityAddNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.back.setOnClickListener {
            finish()
        }


        binding.save.setOnClickListener {
            val title = binding.title.text.toString()
            val discription = binding.discription.text.toString()

            saveNotes(title , discription)
        }
    }

    private fun saveNotes(title: String, description: String) {
        val intent = Intent()
        intent.putExtra("title", title)
        intent.putExtra("description", description)
        setResult(RESULT_OK, intent)
        finish()
    }
}