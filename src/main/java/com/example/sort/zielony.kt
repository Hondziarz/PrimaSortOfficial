package com.example.sort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_zielony.*
import kotlinx.android.synthetic.main.activity_zolty.*
import kotlinx.android.synthetic.main.activity_zolty.button2

class zielony : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zielony)

        button2.setOnClickListener {

            var akt: Intent = Intent(applicationContext, Zasady::class.java)
            startActivity(akt)

        }
    }
}