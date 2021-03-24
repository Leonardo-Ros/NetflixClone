package com.leo.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.leo.netflixclone.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar!!.hide()
        VerificarUsuarioLogado()

        binding.txtTelaCadastro.setOnClickListener{
            val intent= Intent(this,FormCadastro::class.java)
            startActivity(intent)
        }

        binding.btLogin.setOnClickListener{

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val msg_erro= binding.msgError

            if (email.isEmpty() || senha.isEmpty()){
                msg_erro.setText("Preencha todos os campos!")

            }else{
                AutenticarUsuario()

            }
        }
    }

    private fun AutenticarUsuario(){

        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val msg_erro= binding.msgError

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Login Efettuado com Sucesso!",Toast.LENGTH_SHORT).show()
                IrParaTelaFilmes()
            }
        }.addOnFailureListener {

            var erro= it

            when{
                erro is FirebaseAuthInvalidCredentialsException -> msg_erro.setText("Email ou Senha estão incorretos!")
                erro is FirebaseNetworkException -> msg_erro.setText("Sem conexão com a Internet")
                else-> msg_erro.setText("Erro ao logar Usário")
            }


        }

    }

    private fun VerificarUsuarioLogado(){
        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if ( usuarioLogado != null ){
            IrParaTelaFilmes()
        }
    }

    private fun IrParaTelaFilmes(){

        val intent= Intent(this,ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }
}