package com.example.connu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bRegister : Button = findViewById(R.id.bRegister)
        val bLogin : Button = findViewById(R.id.bLogin)
        val tvLostPass : TextView = findViewById(R.id.tvLostPass)

        bRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        tvLostPass.setOnClickListener {
            val intentf = Intent(this, ForgottenPassActivity::class.java)
            startActivity(intentf)
        }

        bLogin.setOnClickListener{
            val login = Intent(this, MainPageActivity::class.java)
            startActivity(login)
        }
    }

}