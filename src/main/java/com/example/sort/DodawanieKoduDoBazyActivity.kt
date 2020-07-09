package com.example.sort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.dodawanie_kodu_do_bazy.*

class DodawanieKoduDoBazyActivity : AppCompatActivity() {

    lateinit var smietnik: String
    lateinit var nazwa: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dodawanie_kodu_do_bazy)

            Init_scan.setOnClickListener {




                val scanner = IntentIntegrator(this)
                scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                scanner.setBeepEnabled(false)

                scanner.initiateScan()




            }

        dodaj_kod_button.setOnClickListener{

            dodawanie_do_bazy()
            Toast.makeText(applicationContext,"Pomyślnie dodano kod do bazy", Toast.LENGTH_LONG).show()




        }
    }


    private fun dodawanie_do_bazy(){

        var intent: Intent = getIntent()
        var kod: String = intent.getStringExtra("kod").toString()



        smietnik = kolor_kosza_na_odpady_edittext.text.toString()
        nazwa = nazwa_odpadu_edittext.text.toString()


        val ref = FirebaseDatabase.getInstance().getReference("kody")

        val codeId = ref.push().key

        val code = kod_odpadu(
            kod,
            nazwa,
            smietnik,
            codeId.toString()
        )
        ref.child(codeId.toString()).setValue(code).addOnCompleteListener {
        }
        finish()






    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        var kod = result.contents.toString()
        Toast.makeText(applicationContext, "kod pomyślnie zeskanowany", Toast.LENGTH_LONG).show()


        var Aktzeskanowanykod: Intent = Intent(applicationContext, DodawanieKoduDoBazyActivity::class.java)
            Aktzeskanowanykod.putExtra("kod", kod)
            startActivity(Aktzeskanowanykod)
    }


}

class kod_odpadu(val kod: String, val nazwa: String, val kosz: String, val Id: String)