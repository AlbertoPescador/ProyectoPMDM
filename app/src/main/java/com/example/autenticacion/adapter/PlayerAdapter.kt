package com.example.listadoparques.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.autenticacion.Jugadores
import com.example.autenticacion.R

class PlayerAdapter(private val listaJugadores:List<Jugadores>) : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return PlayerViewHolder(layoutInflater.inflate(R.layout.listajugadores,parent,false))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = listaJugadores[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return listaJugadores.size
    }
}