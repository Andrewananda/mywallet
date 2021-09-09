package com.devstart.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.devstart.mywallet.auth.signIn.viewModel.SignInViewModel
import com.devstart.mywallet.auth.signUp.viewModel.SignUpViewModel
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.model.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHost.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)

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

        val actionBarConfig = AppBarConfiguration(setOf(R.id.dashboardFragment, R.id.signInFragment, R.id.signUpFragment))
        setupActionBarWithNavController(navController, actionBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHost.navController
        return navController.navigateUp()
    }
}