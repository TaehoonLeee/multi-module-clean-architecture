package org.kumnan.aos.apps.testpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import org.kumnan.aos.apps.testpractice.R
import org.kumnan.aos.apps.testpractice.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_frame_layout) as NavHostFragment
            bottomNavigationView.setupWithNavController(navHostFragment.navController)
            bottomNavigationView.setOnItemReselectedListener {  }
        }
    }
}