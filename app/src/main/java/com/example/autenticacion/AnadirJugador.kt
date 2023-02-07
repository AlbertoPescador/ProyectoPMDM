package com.example.autenticacion

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.autenticacion.databinding.ActivityAnadirJugadorBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class AnadirJugador : ActivityWithMenu() {

    lateinit var storage: FirebaseStorage
    lateinit var imagen : ImageButton
    lateinit var binding : ActivityAnadirJugadorBinding
    val db = FirebaseFirestore.getInstance()

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            uri ->
        if(uri!=null){
            imagen.setImageURI(uri)
        }else {
            //  No se ha seleccionado ninguna imagen
        }
    }

    val pickFoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val image = it.data?.extras?.get("data") as Bitmap
        binding.ibtnImagen.setImageBitmap(image)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnadirJugadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imagen = binding.ibtnImagen

        binding.btnanadirjugador.setOnClickListener{
            val builder = AlertDialog.Builder(this)

            builder.setPositiveButton("Guardar") { dialog, which ->

                if (binding.nombrejugador.text.isNotEmpty() && binding.equipojugador.text.isNotEmpty() && binding.nacionalidadjugador.text.isNotEmpty()) {
                    db.collection("Jugadores")
                        .add(mapOf(
                            "Nickname" to binding.nombrejugador.text.toString()
                            "Equipo" to binding.equipojugador.text.toString()
                            "Nacionalidad" to binding.nacionalidadjugador.text.toString()
                        ))
                }
            }
        }

        //  Cuando pulsemos sobre el imageButton, llamaremos al launcher (pickMedia) para lanzarlo:
        binding.btnAbrirGaleria.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.btnAccederCamara.setOnClickListener{
            pickFoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }
}