package com.app.desafiosicredi.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseActivity
import com.app.desafiosicredi.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var toolBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolBar = binding.toolbar
        setSupportActionBar(toolBar)
        setNavigation()
    }

    private fun setNavigation() {
        val navController = getNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        toolbar.setNavigationOnClickListener {
            getNavController().navigateUp()
        }
    }

    private fun getNavController(): NavController {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }

    fun setToolbarIcon() {
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back)
    }
}
