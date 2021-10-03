package com.example.roomloginapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.roomloginapp.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var container: ConstraintLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initView()

        val handler: Handler = Handler()

        Thread() {
            var i = 0
            while (i <= 400) {
                i += 20
                handler.post {
                    progressBar.progress = i
                }
                if (i == 400) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    // init fragment views
    private fun initView() {
        container = findViewById(R.id.container)
        progressBar = findViewById(R.id.progressBar)
    }
}