package com.leo.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.leo.netflixclone.Adapter.FilmesAdapter
import com.leo.netflixclone.Model.addfilmes
import com.leo.netflixclone.databinding.ActivityDetalhesFilmeBinding
import com.squareup.picasso.Picasso

class DetalhesFilme : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        val recycler_outrosfilmes= binding.reclycerOutrosfilmes
        recycler_outrosfilmes.adapter = FilmesAdapter(addfilmes())
        recycler_outrosfilmes.layoutManager= GridLayoutManager(applicationContext,3)


        val capawitcher = "https://firebasestorage.googleapis.com/v0/b/netflixclone-leor.appspot.com/o/IMG_20201024_100546.jpg?alt=media&token=a7d57c11-2f90-4748-aecd-ac1e9fed9e71"
        Picasso.get().load(capawitcher).fit().into(binding.capa)

        binding.playVideo.setOnClickListener {
            val intent = Intent(this,Video::class.java)
            startActivity(intent)
        }
    }

    private fun Toolbar(){
        val toolbar_detalhes = binding.toolbarDetalhes
        toolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_back))
        toolbar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this,ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}