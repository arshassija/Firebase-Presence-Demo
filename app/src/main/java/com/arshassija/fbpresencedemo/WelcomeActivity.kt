package com.arshassija.fbpresencedemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arshassija.fbpresencedemo.databinding.ActivityMainBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            if (name.length > 2) {
                startActivity(Intent(this, UserListActivity::class.java).putExtra("name", binding.etName.text.toString()))
            } else {
                binding.etName.error = "name should have at least 3 characters"
            }
        }
    }

}