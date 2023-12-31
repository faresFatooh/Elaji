package com.example.elajimaneger.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.elajimaneger.R

class BaseViewModel : ViewModel() {

    private val _navController = MutableLiveData<NavController>()
    val navController: LiveData<NavController> = _navController

    fun setNavController(navController: NavController) {
        _navController.value = navController
    }

    fun onHomeNavigationSelected() {
        navigateToDestination(R.id.navigation_home)
    }

    fun onDashboardNavigationSelected() {
        navigateToDestination(R.id.navigation_dashboard)
    }

    fun onNotificationsNavigationSelected() {
        navigateToDestination(R.id.navigation_notifications)
    }

    fun onProfileNavigationSelected() {
        navigateToDestination(R.id.navigation_profile)
    }
    fun onCategoriesNavigationSelected() {
        navigateToDestination(R.id.navigation_categories)
    }

    private fun navigateToDestination(destinationId: Int) {
        _navController.value?.navigate(destinationId)
    }
}
