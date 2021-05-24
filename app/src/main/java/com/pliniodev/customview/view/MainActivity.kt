package com.pliniodev.customview.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.pliniodev.customview.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressButton.setOnClickListener {
            binding.progressButton.setButton()
            //simulando chamada a API
            lifecycleScope.launch {
                delay(3000)
                binding.progressButton.setButton()
            }
        }

        binding.nextButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, ChordViewActivity::class.java))
        }

    }
}