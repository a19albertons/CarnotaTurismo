package com.example.carnotaturismo.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.example.carnotaturismo.R

class MusicService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        // Inicializa el MediaPlayer cuando el servicio se crea por primera vez.
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
        mediaPlayer?.isLooping = true // Para que la música se repita
        mediaPlayer?.setVolume(1f, 1f) // Ajusta el volumen si quieres
        Log.d("MusicService", "Servicio de música creado y MediaPlayer inicializado.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Se llama cada vez que se inicia el servicio con startService().
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
            Log.d("MusicService", "Música iniciada.")
        }
        // START_STICKY hace que el servicio se recree si el sistema lo mata por falta de memoria.
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Libera los recursos del MediaPlayer cuando el servicio se destruye.
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        Log.d("MusicService", "Servicio de música destruido y MediaPlayer liberado.")
    }
}