package com.example.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Detalle : AppCompatActivity() {

    val database = Firebase.database
    val myRef = database.getReference("peliculas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        var nombre = findViewById<EditText>(R.id.nombre_peli)
        var genero = findViewById<EditText>(R.id.genero_peli)
        var anio = findViewById<EditText>(R.id.anio_peli)
        var duracion = findViewById<EditText>(R.id.duracion_peli)
        var imagen = findViewById<ImageView>(R.id.imagen)
        var editar = findViewById<Button>(R.id.editar_peli)
        var eliminar = findViewById<Button>(R.id.eliminar_peli)
        val parametros = intent.extras

        nombre.setText(parametros?.getCharSequence("nombre").toString())
        genero.setText(parametros?.getCharSequence("genero").toString())
        anio.setText(parametros?.getCharSequence("anio").toString())
        duracion.setText(parametros?.getCharSequence("duracion").toString())

        if (parametros?.getCharSequence("genero") == "terror"){
            imagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.miedo))
        }
        else if(parametros?.getCharSequence("genero") == "comedia"){
            imagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.risas))
        }
        else{
            imagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.desconocido))
        }

        editar.setOnClickListener{
            var pelicula = PeliCampos(nombre.text.toString(),genero.text.toString(), anio.text.toString(), duracion.text.toString())
            myRef.child(parametros?.getCharSequence("id").toString()).setValue(pelicula).addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Pelicula editada con exito!", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "Error: "+ task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        eliminar.setOnClickListener{

        }
    }


}