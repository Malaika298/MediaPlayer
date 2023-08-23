package com.example.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.example.mediaplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    var totalTime :Int = 0

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)

        mediaPlayer.setVolume(1f, 1f)
        totalTime = mediaPlayer.duration

        binding.imageViewPlay.setOnClickListener {

            mediaPlayer.start()
        }

        binding.imageViewPause.setOnClickListener {

            mediaPlayer.pause()
        }

        binding.imageViewStop.setOnClickListener {


            mediaPlayer.stop()

            mediaPlayer.reset()

            mediaPlayer.release()



        }



        binding.seekBar.max = totalTime

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (fromUser){

                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }


        })

        val handler = Handler()

        handler.postDelayed(object  : Runnable{
            override fun run() {

                try {
                    binding.seekBar.progress = mediaPlayer.currentPosition

                    handler.postDelayed(this, 1000)
                }catch (exception : Exception){

                    binding.seekBar.progress = 0
                }

            }


        },0)
    }
}