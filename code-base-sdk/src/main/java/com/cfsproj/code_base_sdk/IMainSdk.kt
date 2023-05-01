package com.cfsproj.code_base_sdk

import android.content.Context
import android.content.Intent
import android.util.Log
import com.cfsproj.code_base_sdk.ui.BaseMainActivity
import com.cfsproj.code_base_sdk.utils.AppType

interface IMainSdk {
    fun launchApplication(context: Context, appType: AppType)
}

object MainSdk: IMainSdk {
    override fun launchApplication(context: Context, appType: AppType) {
        Intent(context, BaseMainActivity::class.java).apply {
            Log.d("TAG", "launchApplication: sadasd")
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("APP_TYPE", appType)
            context.startActivity(this)

        }
    }
}