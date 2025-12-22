package com.example.aplikasipertama

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardActivity : AppCompatActivity() {
    private lateinit var userDao: UserDao
    public var id: Int = 0 //penambahan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        //val username = intent.getStringExtra("USERNAME")
        //val email = intent.getStringExtra("EMAIL")
        //val nama_depan = intent.getStringExtra("NAMA DEPAN")
        //val nama_belakang = intent.getStringExtra("NAMA BELAKANG")

        val db = AbsensiDatabase.getDatabase(this)
        userDao = db.userDao()

        id = intent.getIntExtra("ID", 0)

        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvFirstname = findViewById<TextView>(R.id.tvFirstname)
        val tvLastname = findViewById<TextView>(R.id.tvLastname)
        val tvAlamat = findViewById<TextView>(R.id.tvAlamat)

        lifecycleScope.launch(Dispatchers.IO){
            val user = userDao.getUserById(id)
            withContext(Dispatchers.Main){
                tvUsername.text = user.username
                tvEmail.text = user.email
                tvFirstname.text = user.namadepan
                tvLastname.text = user.namadepan
                tvAlamat.text = user.alamat
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}