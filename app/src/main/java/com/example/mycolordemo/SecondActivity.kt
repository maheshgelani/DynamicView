package com.example.mycolordemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.GridLayout


class SecondActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var lastBlueButton: Button
    private val clickedIndexes = mutableSetOf<Int>()
    private var totalButtons: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        gridLayout = findViewById(R.id.gridLayout)
        totalButtons = intent.getIntExtra("numButtons", 0)

        gridLayout.rowCount = totalButtons/3 + 1

        for (i in 0 until totalButtons) {

            val button = Button(this)
            button.text = "Button ${i + 1}"
            button.setBackgroundColor(Color.WHITE) // Set initial color to white

            button.setOnClickListener {
                changeButtonColor(it as Button)
            }

            val spacingInDp = 8
            val spacingInPixels = (spacingInDp * resources.displayMetrics.density).toInt()

            val params = GridLayout.LayoutParams()
            params.setMargins(spacingInPixels, spacingInPixels, spacingInPixels, spacingInPixels)

            button.layoutParams = params
            gridLayout.addView(button)
        }

        randomlyMakeButtonBlue()
    }

    private fun randomlyMakeButtonBlue() {
        val remainingButtons = totalButtons - clickedIndexes.size
        if (remainingButtons == 0) {
            // All buttons are red
            return
        }

        var randomIndex: Int
        do {
            randomIndex = (0 until totalButtons).random()
        } while (clickedIndexes.contains(randomIndex))

        lastBlueButton = gridLayout.getChildAt(randomIndex) as Button
        lastBlueButton.setBackgroundColor(Color.BLUE)
    }

    private fun changeButtonColor(button: Button) {
        if (button == lastBlueButton) {
            button.setBackgroundColor(Color.RED)
            clickedIndexes.add(gridLayout.indexOfChild(button))
            randomlyMakeButtonBlue()
        }
    }
}
