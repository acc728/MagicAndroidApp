package com.hiberus.magicandroidapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hiberus.magicandroidapp.R
import com.hiberus.magicandroidapp.databinding.ActivityMainBinding
import com.hiberus.magicandroidapp.presentation.fragment.TabFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadFragment(TabFragment())
    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fcv_main, fragment).commit()
    }
}