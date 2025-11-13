package com.example.medalert

import android.app.Application
import com.example.medalert.data.AppContainer
import com.example.medalert.data.AppDataContainer

class MedAlertApplication : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}