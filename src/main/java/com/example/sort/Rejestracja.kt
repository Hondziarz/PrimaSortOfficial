package com.example.sort

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rejestracja.*
import com.google.firebase.auth.FirebaseAuth

class Rejestracja : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rejestracja)

        zarejestruj.setOnClickListener{
            performRegister()
        }
    }

    private fun performRegister() {
        val email = email.text.toString()
        val password = haslo.text.toString()
        val repeat_password = powtorz_haslo.text.toString()

        if (email.isEmpty() || password.isEmpty() || "@" !in email){
            Toast.makeText(this, "niepoprawny login/hasło", Toast.LENGTH_LONG).show()
        }
        else if (password != repeat_password){
            Toast.makeText(this, "hasła nie są takie same", Toast.LENGTH_LONG).show()
        }

        else if (regulamin.isChecked) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful)

                        Toast.makeText(
                            applicationContext,
                            "Pomyślnie utworzono konto, można sie zalogować",
                            Toast.LENGTH_SHORT
                        ).show()
                    finish()
                    return@addOnCompleteListener

                }
        }else{
            Toast.makeText(applicationContext, "Zaakceptuj Regulamin", Toast.LENGTH_SHORT).show()}
    }
}