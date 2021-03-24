package com.leo.netflixclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.leo.netflixclone.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        Toolbar()

        binding.btCad.setOnClickListener{

            val email= binding.editEmail.text.toString()
            val senha= binding.editSenha.text.toString()
            val msg_erro= binding.msgError

            if( email.isEmpty() || senha.isEmpty()){
                msg_erro.setText("Preencha todos os campos!")
            } else{
                CadastrarUsuario()
            }
        }



    }

    private  fun CadastrarUsuario(){

        val email= binding.editEmail.text.toString()
        val senha= binding.editSenha.text.toString()
        val msg_erro= binding.msgError

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Usário cadastrado com sucesso!",Toast.LENGTH_SHORT).show()
                binding.editEmail.setText("")
                binding.editSenha.setText("")
                msg_erro.setText(" ")
            }
        }.addOnFailureListener {

            var erro = it

            when{
                erro is FirebaseAuthWeakPasswordException -> msg_erro.setText("Digite uma senha com no minimo 6 caracteres")
                erro is FirebaseAuthUserCollisionException -> msg_erro.setText("Email ja cadastrado, favor tente outro!")
                erro is FirebaseNetworkException -> msg_erro.setText("Favor verifique sua conexão com a internet")
                else -> msg_erro.setText("Erro ao cadastrar o usuário")
            }

        }
    }

    private fun Toolbar(){
        val toolbar = binding.toolbarCad
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo))
    }
}