package com.example.sort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_logowanie.*

class Logowanie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logowanie)

        zarejestruj.setOnClickListener {
            var akt: Intent = Intent(applicationContext, Rejestracja::class.java)
            startActivity(akt)
        }
        zaloguj.setOnClickListener {
            val email = login.text.toString()
            val password = haslo.text.toString()
            Log.d("Login", "attempt login with email/pw: $email/***")
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "Pomyślnie zalogowano", Toast.LENGTH_LONG).show()
                        }
                })
        }

    }
}