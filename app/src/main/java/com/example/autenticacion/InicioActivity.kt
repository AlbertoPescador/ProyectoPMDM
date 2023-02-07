package com.example.autenticacion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autenticacion.databinding.ActivityListadoBinding
import com.example.listadoparques.adapter.PlayerAdapter
import com.google.firebase.firestore.FirebaseFirestore

class InicioActivity : ActivityWithMenu() {
    val db = FirebaseFirestore.getInstance()
    private lateinit var binding : ActivityListadoBinding
    private lateinit var adapterJugadores: PlayerAdapter
    private lateinit var listaJugadores:ArrayList<Jugadores>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterJugadores = PlayerAdapter(listaJugadores)
        listaJugadores = ArrayList()

        db.collection("Jugadores").get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    val player = document.toObject(Jugadores::class.java)
                    player.Nickname = document["Nickname"].toString()
                    player.Equipo = document["Equipo"].toString()
                    player.Nacionalidad = document["Nacionalidad"].toString()
                    player.Foto = document["Foto"].toString()
                    listaJugadores.add(player)
                }

                binding.recyclerjugadores.layoutManager = LinearLayoutManager(this)
                binding.recyclerjugadores.adapter = adapterJugadores
            }
    }
}