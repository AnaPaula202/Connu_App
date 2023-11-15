package com.example.connu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ForgottenPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotten_pass)

        val tvGobackFP: TextView = findViewById(R.id.tvGobackFPass)
        val ivGobackFP: ImageView = findViewById(R.id.ivGobackFPass)

        tvGobackFP.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ivGobackFP.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}