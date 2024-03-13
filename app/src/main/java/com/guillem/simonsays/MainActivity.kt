package com.guillem.simonsays
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.SoundPool
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SimonSaysView.CircleClickListener {
    private val circleColors = listOf(Color.GREEN, Color.BLUE, Color.YELLOW, Color.RED)
    private val gameSequence = mutableListOf<Int>()
    private val playerSequence = mutableListOf<Int>()
    private lateinit var soundPool: SoundPool
    private var currentSequenceLength: Int = 0
    private val soundIds = IntArray(4)
    private lateinit var simonSaysView: SimonSaysView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SoundPool
        soundPool = SoundPool.Builder().setMaxStreams(4).build()
        soundIds[0] = soundPool.load(this, R.raw.soundgreen, 1)
        soundIds[1] = soundPool.load(this, R.raw.soundblue, 1)
        soundIds[2] = soundPool.load(this, R.raw.soundyellow, 1)
        soundIds[3] = soundPool.load(this, R.raw.redsound, 1)

        // Set up SimonSaysView
        simonSaysView = findViewById(R.id.simonSaysView)
        simonSaysView.setOnCircleClickListener(this)

        playSequence()
    }

    override fun onCircleClicked(color: Int) {
        playerSequence.add(circleColors.indexOf(color))
        checkSequence()
        soundPool.play(soundIds[circleColors.indexOf(color)], 1.0f, 1.0f, 1, 0, 1.0f)
    }



    private fun playSequence() {
        var currentIndex = 0
        lifecycleScope.launch {
            repeat(currentSequenceLength) {
                val buttonToLightUp = gameSequence[currentIndex]
                soundPool.play(soundIds[buttonToLightUp], 1.0f, 1.0f, 1, 0, 1.0f)
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
                playerSequence.clear()
                gameSequence.clear()
                currentSequenceLength = 0
            }
        }
    }

    private fun stopSequence() {
        playerSequence.clear()
        // Enabling buttons is not needed since we are using canvas clicks
    }
}