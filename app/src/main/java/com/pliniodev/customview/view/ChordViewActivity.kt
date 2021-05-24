package com.pliniodev.customview.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pliniodev.customview.databinding.ActivityChordViewBinding

class ChordViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChordViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextViewButtom.setOnClickListener {
            startActivity(Intent(this@ChordViewActivity, SmileActivity::class.java))
        }
    }
}