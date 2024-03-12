package com.guillem.simonsays


import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class MainActivity : AppCompatActivity() {
    private val gameSequence = mutableListOf<Int>()
    private val playerSequence = mutableListOf<Int>()
    private lateinit var soundPool: SoundPool
    private var currentSequenceLength: Int = 0
    private val soundIds = IntArray(4)
    private lateinit var coroutine: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SoundPool
        soundPool = SoundPool.Builder().setMaxStreams(4).build()
        soundIds[0] = soundPool.load(this, R.raw.soundgreen, 1)
        soundIds[1] = soundPool.load(this, R.raw.soundblue, 1)
        soundIds[2] = soundPool.load(this, R.raw.soundyellow, 1)
        soundIds[3] = soundPool.load(this, R.raw.redsound, 1)



        // Set up button listeners
        val buttonBlue = findViewById<Button>(R.id.buttonBlue)
        val buttonYellow = findViewById<Button>(R.id.buttonYellow)
        val buttonGreen = findViewById<Button>(R.id.buttonGreen)
        val buttonRed = findViewById<Button>(R.id.buttonRed)

        buttonBlue.setOnClickListener {
            playerSequence.add(1)
            checkSequence()
            soundPool.play(soundIds[1], 1.0f, 1.0f, 1, 0, 1.0f)
            lightUpButton(buttonBlue)
        }
        buttonYellow.setOnClickListener {
            playerSequence.add(2)
            checkSequence()
            soundPool.play(soundIds[2], 1.0f, 1.0f, 1, 0, 1.0f)
            lightUpButton(buttonYellow)
        }
        buttonGreen.setOnClickListener {
            playerSequence.add(0)
            checkSequence()
            soundPool.play(soundIds[0], 1.0f, 1.0f, 1, 0, 1.0f)
            lightUpButton(buttonGreen)
        }
        buttonRed.setOnClickListener {
            playerSequence.add(3)
            checkSequence()
            soundPool.play(soundIds[3], 1.0f, 1.0f, 1, 0, 1.0f)
            lightUpButton(buttonRed)
        }
        playSequence()
    }

    private fun disableButtons() {
        findViewById<Button>(R.id.buttonBlue).isEnabled = false
        findViewById<Button>(R.id.buttonYellow).isEnabled = false
        findViewById<Button>(R.id.buttonGreen).isEnabled = false
        findViewById<Button>(R.id.buttonRed).isEnabled = false
    }

    private fun enableButtons() {
        findViewById<Button>(R.id.buttonBlue).isEnabled = true
        findViewById<Button>(R.id.buttonYellow).isEnabled = true
        findViewById<Button>(R.id.buttonGreen).isEnabled = true
        findViewById<Button>(R.id.buttonRed).isEnabled = true
    }

    private fun playSequence() {
        disableButtons()
        var currentIndex = 0
        coroutine = lifecycleScope.launch {
            repeat(currentSequenceLength) {
                val buttonToLightUp = when (gameSequence[currentIndex]) {
                    0 -> findViewById<Button>(R.id.buttonGreen)
                    1 -> findViewById<Button>(R.id.buttonBlue)
                    2 -> findViewById<Button>(R.id.buttonYellow)
                    3 -> findViewById<Button>(R.id.buttonRed)
                    else -> null
                }
                buttonToLightUp?.let { lightUpButton(it) }
                soundPool.play(soundIds[gameSequence[currentIndex]], 1.0f, 1.0f, 1, 0, 1.0f)
                currentIndex++
                delay(1000)
            }
            val newNumber = (0..3).random()
            gameSequence.add(newNumber)
            stopSequence()
        }
    }

    private fun checkSequence() {
        if (playerSequence.size == gameSequence.size) {
            if (playerSequence == gameSequence) {
                currentSequenceLength++
                playerSequence.clear()
                playSequence()
            } else {
                // Game Over
                // Implement your game over logic here
            }
        }
    }

    private fun stopSequence() {
        playerSequence.clear()
        enableButtons()
    }

    private fun lightUpButton(button: Button) {

    }


}