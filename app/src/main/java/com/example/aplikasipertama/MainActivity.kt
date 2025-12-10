package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = AbsensiDatabase.getDatabase(this)
        userDao = db.userDao()

        val username = findViewById<EditText>(R.id.editTextUsername)
        val password = findViewById<EditText>(R.id.editTextPassword)

        var buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        buttonSubmit.setOnClickListener {
            var usernameText = username.text.toString()
            var passwordText = password.text.toString()

            if(usernameText.isBlank() || passwordText.isBlank()){
                Toast.makeText(this,
                    "Username & Password tidak boleh kosong",
                Toast.LENGTH_LONG
                ).show()
            }else{

                lifecycleScope.launch(Dispatchers.IO){
                    val user = userDao.getUserByUsernameAndPassword(usernameText, passwordText)
                    withContext(Dispatchers.Main) {
                        if(user != null) {
                            Toast.makeText(
                                this@MainActivity, "Username & Password Ditemukan",
                                Toast.LENGTH_LONG)
                                .show()

                            val intentPindahDashboard = Intent(this@MainActivity, DashboardActivity::class.java)
                            intentPindahDashboard.putExtra("ID", user.id)
                            startActivity(intentPindahDashboard)

                        } else {
                            Toast.makeText(
                                this@MainActivity, "Username & Password Salah",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                    }

            }
        }

        val buttonDaftar = findViewById<Button>(R.id.buttonDaftar)

        buttonDaftar.setOnClickListener {
            val intentPindahPendaftaran = Intent(this, PendaftaranActivity::class.java)
            startActivity(intentPindahPendaftaran)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}