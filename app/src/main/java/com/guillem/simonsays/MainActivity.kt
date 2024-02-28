package com.guillem.simonsays

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    private var soundIds = IntArray(4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gameIndex : Int[] = Int[]
        val soundPool : SoundPool = SoundPool.Builder().setMaxStreams(4).build();
        soundIds[0] = soundPool.load(this, R.raw.soundgreen, 1)
        soundIds[1] = soundPool.load(this, R.raw.soundblue, 1)
        soundIds[2] = soundPool.load(this, R.raw.soundyellow, 1)
        soundIds[3] = soundPool.load(this, R.raw.redsound, 1)
        findViewById<Button>(R.id.buttonBlue).setOnClickListener(){
            soundPool.play(soundIds[1], 1.0f, 1.0f, 1, 0, 1.0f)
        }
        findViewById<Button>(R.id.buttonYellow).setOnClickListener(){
            soundPool.play(soundIds[2], 1.0f, 1.0f, 1, 0, 1.0f)
        }
        findViewById<Button>(R.id.buttonGreen).setOnClickListener(){
            soundPool.play(soundIds[0], 1.0f, 1.0f, 1, 0, 1.0f)
        }
        findViewById<Button>(R.id.buttonRed).setOnClickListener(){
            soundPool.play(soundIds[3], 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }
}