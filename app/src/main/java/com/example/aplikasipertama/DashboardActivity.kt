package com.example.aplikasipertama

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val username = intent.getStringExtra("USERNAME")
        val email = intent.getStringExtra("EMAIL")
        val nama_depan = intent.getStringExtra("NAMA DEPAN")
        val nama_belakang = intent.getStringExtra("NAMA BELAKANG")

        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvFirstname = findViewById<TextView>(R.id.tvFirstname)
        val tvLastname = findViewById<TextView>(R.id.tvLastname)

        tvUsername.text = username
        tvEmail.text = email
        tvFirstname.text = nama_depan
        tvLastname.text = nama_belakang

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}