package com.example.autenticacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.autenticacion.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnregistrarse.setOnClickListener{
            registrar()
        }
    }

    fun registrar(){
        //  Si los campos de correo y password no están vacíos:
        if (binding.nombre.text.isNotEmpty() && binding.apellidos.text.isNotEmpty() && binding.email.text.toString().isNotEmpty() && binding.password.text.toString().isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.email.text.toString(),
                binding.password.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else {
                    Toast.makeText(this, "Error en el registro del nuevo usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else {
            Toast.makeText(this,"Algún campo está vacío", Toast.LENGTH_SHORT).show()
        }
    }
}