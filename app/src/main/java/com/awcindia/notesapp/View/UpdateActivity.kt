package com.awcindia.notesapp.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awcindia.notesapp.R
import com.awcindia.notesapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {


    lateinit var binding: ActivityUpdateBinding

    var currentId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title = intent.getStringExtra("titleUpdate") ?: ""
        val description = intent.getStringExtra("descriptionUpdate") ?: ""
        currentId = intent.getIntExtra("idUpdate", -1)

        binding.titleUpdate.setText(title)
        binding.discriptionUpdate.setText(description)

        binding.backUpdate.setOnClickListener {
            finish()
        }

        binding.saveUpdate.setOnClickListener {
            val titles = binding.titleUpdate.text.toString()
            val discription = binding.discriptionUpdate.text.toString()

            updateData(titles, discription)
        }

    }

    private fun updateData(titles: String, discription: String) {
        val intent = Intent()
        intent.putExtra("title2", titles)
        intent.putExtra("description2", discription)

        if (currentId != -1) {
            intent.putExtra("id2", currentId)
            setResult(RESULT_OK, intent)
            finish()
        }
        else{
            Toast.makeText(this, "Data has been not updated" , Toast.LENGTH_SHORT).show()
        }

    }
}