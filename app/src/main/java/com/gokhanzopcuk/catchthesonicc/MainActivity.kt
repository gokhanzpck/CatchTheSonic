package com.gokhanzopcuk.catchthesonicc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gokhanzopcuk.catchthesonicc.databinding.ActivityMainBinding
import com.gokhanzopcuk.catchthesonicc.ui.DifficultActivity
import com.gokhanzopcuk.catchthesonicc.ui.EasyActivity
import com.gokhanzopcuk.catchthesonicc.ui.MediumActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
         binding.easyButton.setOnClickListener {
           val intent=Intent(this,EasyActivity::class.java)
             startActivity(intent)
         }
        binding.mediumButton.setOnClickListener {
            val intent=Intent(this,MediumActivity::class.java)
            startActivity(intent)
        }
        binding.difficultButton.setOnClickListener {
            val intent=Intent(this,DifficultActivity::class.java)
            startActivity(intent)
        }

    }
}