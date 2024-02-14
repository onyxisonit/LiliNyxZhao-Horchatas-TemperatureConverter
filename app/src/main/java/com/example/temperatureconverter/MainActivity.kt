package com.example.temperatureconverter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.SeekBar
import android.widget.TextView
import org.w3c.dom.Text

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var seekBarCelsius = findViewById<SeekBar>(R.id.seekBarCelsius)
        var seekBarFahrenheit = findViewById<SeekBar>(R.id.seekBarFahrenheit)
        var numFahrenheit = findViewById<TextView>(R.id.numFahrenheit)
        var numCelsius = findViewById<TextView>(R.id.numCelsius)
        var textInteresting = findViewById<TextView>(R.id.textInteresting)

        // Set initial temperature values
        var celsiusTemperature = 0.0
        var fahrenheitTemperature = 32.0
        var interestingMessage = ""
        // Set SeekBar range and initial progress
        seekBarCelsius.max = 100
        seekBarFahrenheit.max = 212
        seekBarFahrenheit.progress = 32 // Initial value in Fahrenheit

        // Celsius SeekBar listener
        seekBarCelsius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update Celsius temperature
                celsiusTemperature = progress.toDouble()
                numCelsius.text = celsiusTemperature.toString()
                // Update Fahrenheit SeekBar
                fahrenheitTemperature = (celsiusTemperature * 9 / 5) + 32
                seekBarFahrenheit.progress = fahrenheitTemperature.toInt()
                numFahrenheit.text = fahrenheitTemperature.toString()

                // Update interesting message
                interestingMessage = updateInterestingMessage(celsiusTemperature)
                textInteresting.text = interestingMessage
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Fahrenheit SeekBar listener
        seekBarFahrenheit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update Fahrenheit temperature
                fahrenheitTemperature = progress.toDouble()

                // Ensure Fahrenheit temperature doesn't go below 32oF
                if (fahrenheitTemperature < 32) {
                    fahrenheitTemperature = 32.0
                    seekBarFahrenheit.progress = fahrenheitTemperature.toInt()
                    numFahrenheit.text = fahrenheitTemperature.toString()
                }

                // Update Celsius SeekBar
                celsiusTemperature = (fahrenheitTemperature - 32) * 5 / 9
                seekBarCelsius.progress = celsiusTemperature.toInt()

                // Update interesting message
                interestingMessage = updateInterestingMessage(celsiusTemperature)
                textInteresting.text = interestingMessage
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


    }
    fun updateInterestingMessage(celsiusTemperature: Double): String {
        if (celsiusTemperature <= 20) {
            return "I wish it were warmer."
        } else {
            return "I wish it were colder."
        }

    }

}
