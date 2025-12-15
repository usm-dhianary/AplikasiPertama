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

class PendaftaranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pendaftaran)

        val username = findViewById<EditText>(R.id.editTextUsername)
        val email = findViewById<EditText>(R.id.editTextEmail)
        val firstname = findViewById<EditText>(R.id.editFirstName)
        val lastname = findViewById<EditText>(R.id.editLastName)
        val password = findViewById<EditText>(R.id.editPassword)
        val passwordlagi = findViewById<EditText>(R.id.editPasswordLagi)

        var buttonSubmit = findViewById<Button>(R.id.btnSubmit)
        buttonSubmit.setOnClickListener {
            var usernameText = username.text.toString()
            var emailText = email.text.toString()
            var firstnameText = firstname.text.toString()
            var lastnameText = lastname.text.toString()
            var passwordText = password.text.toString()
            var passwordlagiText = passwordlagi.text.toString()


            if (usernameText.isBlank() || emailText.isBlank() || firstnameText.isBlank() || lastnameText.isBlank() || passwordText.isBlank() || passwordlagiText.isBlank()) {
                Toast.makeText(
                    this,
                    "Semua Filed Input Harus Diisi",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val fullName = "$firstnameText $lastnameText".trim()
                val displayName = if (usernameText.isNotBlank()) usernameText else fullName

                //ini harus didalam logika password sama
                val userEntity = UserEntity(
                    username = usernameText,
                    email = emailText,
                    namadepan = firstnameText,
                    namabelakang = lastnameText,
                    password = passwordText
                )

                val db = AbsensiDatabase.getDatabase(this)
                lifecycleScope.launch(Dispatchers.IO){
                    db.userDao().insertUser(userEntity)
                }

                Toast.makeText(
                    this,
                    "User $displayName berhasil didaftarkan",
                    Toast.LENGTH_LONG
                ).show()

                //pindah ke halaman dashborad
//                val intentPindahDashboard = Intent(this, DashboardActivity::class.java)
//
//                intentPindahDashboard.putExtra("USERNAME", usernameText)
//                intentPindahDashboard.putExtra("EMAIL", emailText)
//                intentPindahDashboard.putExtra("NAMA DEPAN", firstnameText)
//                intentPindahDashboard.putExtra("NAMA BELAKANG", lastnameText)
//
//                startActivity(intentPindahDashboard)




            }
        }

        val buttonCancel = findViewById<Button>(R.id.btnCancel)
        buttonCancel.setOnClickListener{
            val intentPindahLogin = Intent(this, MainActivity::class.java)
            startActivity(intentPindahLogin)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}