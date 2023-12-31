package com.example.elajimaneger.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.elajimaneger.R
import com.example.elajimaneger.databinding.ActivityBaseBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

// This BaseActivity is in charge of the user interface pieces, often known as (the manager of our app screens).
class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding
    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupViewModel()
        setupNavigation()
        setupBottomNavigationView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[BaseViewModel::class.java]
        viewModel.setNavController(findNavController(R.id.nav_host_fragment_activity_base))
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_activity_base)
        viewModel.setNavController(navController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_categories,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigationView() {
        viewModel.navController.observe(this) { navController ->
            binding.navView.setupWithNavController(navController)
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)

        val navigationActions: MutableMap<Int, () -> Unit> = HashMap()
        navigationActions[R.id.navigation_home] = {
            viewModel.onHomeNavigationSelected()
        }
        navigationActions[R.id.navigation_dashboard] = {
            viewModel.onDashboardNavigationSelected()
        }
        navigationActions[R.id.navigation_notifications] = {
            viewModel.onNotificationsNavigationSelected()
        }
        navigationActions[R.id.navigation_profile] = {
            viewModel.onProfileNavigationSelected()
        }
        navigationActions[R.id.navigation_categories] = {
            viewModel.onCategoriesNavigationSelected()
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            val itemId = item.itemId
            if (navigationActions.containsKey(itemId)) {
                navigationActions[itemId]
                true
            } else {
                false
            }
        }

        // Set the default selected item
        bottomNavigationView.selectedItemId = R.id.navigation_home
    }

}
