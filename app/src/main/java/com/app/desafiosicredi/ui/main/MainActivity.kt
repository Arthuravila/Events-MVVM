package com.app.desafiosicredi.ui.main

import android.os.Bundle
import com.app.desafiosicredi.R
import com.app.desafiosicredi.core.base.BaseActivity
import com.app.desafiosicredi.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var toolBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolBar = binding.toolbar
        setSupportActionBar(toolBar)
    }
}