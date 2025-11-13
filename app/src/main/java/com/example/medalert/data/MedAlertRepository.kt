package com.example.medalert.data

import kotlinx.coroutines.flow.Flow

interface MedAlertRepository {
    
    fun getAllReminders(): Flow<List<Reminder>>

    suspend fun insertReminder(reminder: Reminder): Long

}