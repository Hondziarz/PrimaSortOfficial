package com.example.sort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_czarny.*
import kotlinx.android.synthetic.main.activity_zolty.*
import kotlinx.android.synthetic.main.activity_zolty.button2

class czarny : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_czarny)
        button2.setOnClickListener {

            var akt: Intent = Intent(applicationContext, Zasady::class.java)
            startActivity(akt)

        }
    }
}