package com.example.myapplication

import android.os.Bundle
<<<<<<< HEAD
import com.google.android.material.bottomnavigation.BottomNavigationView
=======
>>>>>>> minji
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding
<<<<<<< HEAD

class MainActivity : AppCompatActivity() { //fragactivity 상속,activity 위 fragment 알아서 가져온다
=======
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
>>>>>>> minji

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
<<<<<<< HEAD
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
=======

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

>>>>>>> minji
    }
}