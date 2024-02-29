package com.example.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.database

class AgregarPeli : AppCompatActivity() {

    val database = Firebase.database
    val myRef = database.getReference("peliculas")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_peli)

        var nombre = findViewById<EditText>(R.id.agregar_nombre);
        var genero = findViewById<EditText>(R.id.agregar_genero);
        var anio = findViewById<EditText>(R.id.agregar_anio);
        var duracion = findViewById<EditText>(R.id.agregar_duracion);
        var agregar = findViewById<Button>(R.id.agregar_btn);

        agregar.setOnClickListener {
            var pelicula = PeliCampos(nombre.text.toString(), genero.text.toString(), anio.text.toString(), duracion.text.toString());
            myRef.push().setValue(pelicula).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Exito al agregar la Pelicula!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        this,
                        "ERROR" + task.exception!!.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }
}