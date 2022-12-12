package com.example.apiunsplash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apiunsplash.databinding.ActivityCadastroBinding
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCadastrar.setOnClickListener {
            cadastroUsuario()
        }
    }

    private fun cadastroUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        auth.createUserWithEmailAndPassword(
            email,senha
        ).addOnSuccessListener {  authResult->
           val intent = Intent(this, LoginActivity::class.java)
           startActivity(intent)
        }.addOnFailureListener {  exception ->

        }
    }
}