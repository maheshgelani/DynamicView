package com.example.mycolordemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mycolordemo.databinding.ActivityFirstBinding


class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding
    private val viewModel = DataViewModel() // Initialize your ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_first)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.generateButtons.setOnClickListener {
            viewModel.numButtons = binding.numButtonsEditText.text.toString().toIntOrNull()
            val numButtons = viewModel.numButtons
            // Start the second activity with the number of buttons
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("numButtons", numButtons)
            startActivity(intent)
        }
    }
}
