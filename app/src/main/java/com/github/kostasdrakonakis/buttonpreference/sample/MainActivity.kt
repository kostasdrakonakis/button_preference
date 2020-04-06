package com.github.kostasdrakonakis.buttonpreference.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.kostasdrakonakis.buttonpreference.sample.SettingsActivity.Companion.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.button).setOnClickListener { startActivity(this@MainActivity) }
    }
}