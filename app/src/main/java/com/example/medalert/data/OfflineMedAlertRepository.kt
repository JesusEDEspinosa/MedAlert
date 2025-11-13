package com.example.medalert.data

import kotlinx.coroutines.flow.Flow

class OfflineMedAlertRepository(private val reminderDao: ReminderDao) : MedAlertRepository {
    override fun getAllReminders(): Flow<List<Reminder>> =
        reminderDao.getAllReminders()

    override suspend fun insertReminder(reminder: Reminder): Long =
        reminderDao.insertReminder(reminder)
}