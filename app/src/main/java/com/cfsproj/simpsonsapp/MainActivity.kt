package com.cfsproj.simpsonsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cfsproj.code_base_sdk.MainSdk
import com.cfsproj.code_base_sdk.utils.AppType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainSdk.launchApplication(applicationContext, AppType.SIMPSONS)
    }
}