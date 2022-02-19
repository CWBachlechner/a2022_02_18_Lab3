package com.example.a2022_02_07

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible


const val PICK_RAINBOW_COLOR_INTENT = 1  // The request code
const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME" // Key to return rainbow color name in intent
const val RAINBOW_COLOR = "RAINBOW_COLOR" // Key to return rainbow color in intent
const val DEFAULT_COLOR = "#FFFFFF" // White

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.submit_button).setOnClickListener {
            //Set the name of the Activity to launch passing in request code
            Intent(this, RainbowColorPickerActivity::class.java)
                .also { rainbowColorPickerIntent ->
                    val intent = Intent(this, RainbowColorPickerActivity::class.java)
                    getResult.launch(intent)

                }
        }

    }


    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK){
                val backgroundColor = it.data?.getIntExtra(RAINBOW_COLOR, Color.parseColor(DEFAULT_COLOR)) ?: Color.parseColor(DEFAULT_COLOR)
                val colorName = it.data?.getStringExtra(RAINBOW_COLOR_NAME) ?: ""
                val colorMessage = getString(R.string.color_chosen_message, colorName)

                val rainbowColor = findViewById<TextView>(R.id.rainbow_color)

                rainbowColor.setBackgroundColor(ContextCompat.getColor(this, backgroundColor))
                rainbowColor.text = colorMessage
                rainbowColor.isVisible = true
            }
        }

}