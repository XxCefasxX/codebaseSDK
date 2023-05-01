package com.cfsproj.code_base_sdk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.cfsproj.code_base_sdk.R
import com.cfsproj.code_base_sdk.databinding.ActivityBaseMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.cfsproj.code_base_sdk.viewmodel.BaseViewModel

@AndroidEntryPoint
class BaseMainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[BaseViewModel::class.java]
    }

    private val binding by lazy {
        ActivityBaseMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setSupportActionBar(binding.tbToolbar)

        val host = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        setupActionBarWithNavController(host.navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_container).navigateUp() || super.onSupportNavigateUp()
    }

}