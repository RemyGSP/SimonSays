package com.guillem.simonsays

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val gameSequence = mutableListOf<Int>()
    private var playerSequence = mutableListOf<Int>()
    private lateinit var soundPool: SoundPool
    private var currentSequenceLength: Int = 0;
    private var soundIds = IntArray(4)
    private lateinit var coroutine : Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gameIndex : IntArray = IntArray(64)
        var isGamePlaying : Boolean = true;
        var currentGameLength : Int;

        // Initialize SoundPool
        soundPool = SoundPool.Builder().setMaxStreams(4).build()
        soundIds[0] = soundPool.load(this, R.raw.soundgreen, 1)
        soundIds[1] = soundPool.load(this, R.raw.soundblue, 1)
        soundIds[2] = soundPool.load(this, R.raw.soundyellow, 1)
        soundIds[3] = soundPool.load(this, R.raw.redsound, 1)

        // Set up button listeners
        findViewById<Button>(R.id.buttonBlue).setOnClickListener {
            playerSequence.add(1)
            CheckSequence()
            soundPool.play(soundIds[1], 1.0f, 1.0f, 1, 0, 1.0f)
        }
        findViewById<Button>(R.id.buttonYellow).setOnClickListener {
            playerSequence.add(2)
            CheckSequence()
            soundPool.play(soundIds[2], 1.0f, 1.0f, 1, 0, 1.0f)
        }
        findViewById<Button>(R.id.buttonGreen).setOnClickListener {
            playerSequence.add(0)
            CheckSequence()
            soundPool.play(soundIds[0], 1.0f, 1.0f, 1, 0, 1.0f)
        }
        findViewById<Button>(R.id.buttonRed).setOnClickListener {
            playerSequence.add(3)
            CheckSequence()
            soundPool.play(soundIds[3], 1.0f, 1.0f, 1, 0, 1.0f)
        }
        PlaySequence()
    }
    private fun DisableButtons() {
        findViewById<Button>(R.id.buttonBlue).isEnabled = false
        findViewById<Button>(R.id.buttonYellow).isEnabled = false
        findViewById<Button>(R.id.buttonGreen).isEnabled = false
        findViewById<Button>(R.id.buttonRed).isEnabled = false
    }

    private fun EnableButtons() {
        findViewById<Button>(R.id.buttonBlue).isEnabled = true
        findViewById<Button>(R.id.buttonYellow).isEnabled = true
        findViewById<Button>(R.id.buttonGreen).isEnabled = true
        findViewById<Button>(R.id.buttonRed).isEnabled = true
    }

    private fun PlaySequence(){
        DisableButtons()
        var currentIndex = 0;
        coroutine = lifecycleScope.launch {
            for (i in 0..currentSequenceLength){
                soundPool.play(soundIds[gameSequence.get(currentIndex)], 1.0f, 1.0f, 1, 0, 1.0f)
                currentIndex++
                delay(1000)

            }
            val newNumber = (0..3).random();
                gameSequence.add(newNumber)

                StopSequence()


        }
    }

    private fun CheckSequence(){
        for (i in 0..playerSequence.size){
            
        }
    }
    private fun StopSequence(){
        currentSequenceLength++
        coroutine.cancel()
        EnableButtons()
    }

}