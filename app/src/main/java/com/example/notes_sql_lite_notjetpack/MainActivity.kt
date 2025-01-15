package com.example.notes_sql_lite_notjetpack

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes_sql_lite_notjetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // to access fns in ndh
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)
        //seet up adapter
        notesAdapter = NotesAdapter(db.getAllNotes(),  this)

        binding.notesRecyclerView.layoutManager= LinearLayoutManager( this)
        binding.notesRecyclerView.adapter = notesAdapter

        binding.addButton.setOnClickListener {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}
