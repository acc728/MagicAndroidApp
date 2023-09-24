package com.hiberus.magicandroidapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hiberus.magicandroidapp.R
import com.hiberus.magicandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}