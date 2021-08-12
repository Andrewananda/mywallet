package com.devstart.mywallet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.navigation.findNavController

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            if(userExist()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {

                finish()
            }
        }, 3000)
    }

    private fun userExist(): Boolean {
        val sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        return sharedPreferences !== null
    }
}