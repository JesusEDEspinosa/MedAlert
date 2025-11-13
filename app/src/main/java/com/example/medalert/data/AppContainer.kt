package com.example.medalert.data

import android.content.Context

interface AppContainer {
    val medAlertRepository: MedAlertRepository
}

class AppDataContainer(private val context: Context) : AppContainer{
    override val medAlertRepository: MedAlertRepository by lazy {
        val database = MedAlertDatabase.getDatabase(context)
        OfflineMedAlertRepository(database.reminderDao())

    }
}