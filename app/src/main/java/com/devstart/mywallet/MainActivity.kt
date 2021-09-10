package com.devstart.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHost.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav).setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val appBar: ActionBar? = supportActionBar;
            if (destination.id in arrayOf(
                    R.id.splashScreenFragment,
                    R.id.signUpFragment,
                    R.id.signInFragment
            )){
                appBar?.hide()
                findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.GONE
            }else{
                if(destination.id in arrayOf(R.id.dashboardFragment)){
                    appBar?.elevation = 0F
                    appBar?.show()
                }else{
                    appBar?.show()
                }
                findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.VISIBLE
            }
        }

        val actionBarConfig = AppBarConfiguration(setOf(R.id.dashboardFragment,
            R.id.incomeFragment,
            R.id.signInFragment, R.id.signUpFragment))
        setupActionBarWithNavController(navController, actionBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHost.navController
        return navController.navigateUp()
    }
}