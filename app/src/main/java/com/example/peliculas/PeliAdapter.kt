package com.example.peliculas

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.zip.Inflater

class PeliAdapter (private val context: Activity, private val arrayList: ArrayList<Pelicula>)

    : ArrayAdapter<Pelicula>(context, R.layout.item, arrayList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // return super.getView(position, convertView, parent)
        val inflater: LayoutInflater = LayoutInflater.from(context);
        val view: View = inflater.inflate(R.layout.item, null)

        view.findViewById<TextView>(R.id.nombre).text = arrayList[position].nombre;
        view.findViewById<TextView>(R.id.anio).text = arrayList[position].anio;
        view.findViewById<TextView>(R.id.genero).text = arrayList[position].genero;
        view.findViewById<TextView>(R.id.duracion).text = arrayList[position].duracion;

        if (arrayList[position].genero == "terror"){
            view.findViewById<ImageView>(R.id.img).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.miedo))
        }
        else if(arrayList[position].genero == "comedia"){
            view.findViewById<ImageView>(R.id.img).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.risas))
        }
        else{
            view.findViewById<ImageView>(R.id.img).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.desconocido))
        }
        return view
    }
}