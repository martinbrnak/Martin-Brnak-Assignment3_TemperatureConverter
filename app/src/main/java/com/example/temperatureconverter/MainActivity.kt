package com.example.temperatureconverter

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    //implemented with help of chatGPT esp the functions
    private lateinit var seekBarCelsius: SeekBar
    private lateinit var seekBarFahrenheit: SeekBar
    private lateinit var textCelsiusValue: TextView
    private lateinit var textFahrenheitValue: TextView
    private lateinit var textInterestingMessage: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        seekBarCelsius = findViewById(R.id.seekBarCelsius)
        seekBarFahrenheit = findViewById(R.id.seekBarFahrenheit)
        textCelsiusValue = findViewById(R.id.textCelsiusValue)
        textFahrenheitValue = findViewById(R.id.textFahrenheitValue)
        textInterestingMessage = findViewById(R.id.textInterestingMessage)

        seekBarCelsius.max = 100
        seekBarFahrenheit.max = 212




        // Initial values
        updateSeekBarCelsius(0)
        updateSeekBarFahrenheit(32)

        // Celsius seekbar listener
        seekBarCelsius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val fahrenheit = celsiusToFahrenheit(progress)
                updateSeekBarFahrenheit(fahrenheit)
                updateInterestingMessage(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Fahrenheit seekbar listener
        seekBarFahrenheit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress < 32) {
                    seekBarFahrenheit.progress = 32  // Snap back to 32ºF
                } else {
                    val celsius = fahrenheitToCelsius(progress)
                    updateSeekBarCelsius(celsius)
                    updateInterestingMessage(celsius)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateSeekBarCelsius(celsius: Int) {
        textCelsiusValue.text = "$celsius"
        seekBarCelsius.progress = celsius
    }

    private fun updateSeekBarFahrenheit(fahrenheit: Int){
        textFahrenheitValue.text = "$fahrenheit"
        seekBarFahrenheit.progress = fahrenheit
    }

    private fun celsiusToFahrenheit(celsius: Int): Int {
        return ((celsius * 9) / 5) + 32
    }

    private fun fahrenheitToCelsius(fahrenheit: Int): Int {
        return ((fahrenheit - 32) * 5) / 9
    }

    private fun updateInterestingMessage(celsius: Int) {
        if (celsius <= 20) {
            textInterestingMessage.text = getString(R.string.interestingMessageCold)
        } else {
            textInterestingMessage.text = getString(R.string.interestingMessageHot)
        }
    }
}