package com.leo.netflixclone

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.leo.netflixclone.databinding.ActivityVideoBinding

class Video : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val video_url= Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflixclone-leor.appspot.com/o/WhatsApp%20Video%202021-03-18%20at%2012.37.10.mp4?alt=media&token=e1e1fe1b-e915-4dc8-b78f-d2d7b25c8e69")

        val video= binding.video
        video.setMediaController(MediaController(this))
        video.setVideoURI(video_url)
        video.requestFocus()
        video.start()
    }
}