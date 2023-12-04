package com.example.connu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)


        val cancelButton : Button = findViewById(R.id.bCancelNewPost)
        cancelButton.setOnClickListener {
            // Finalizar la actividad y regresar al Fragment
            finish()
        }
    }
}