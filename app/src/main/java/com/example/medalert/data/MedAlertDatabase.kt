package com.example.medalert.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Reminder::class],
    version = 1,
    exportSchema = false,
)
abstract class MedAlertDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var INSTANCE: MedAlertDatabase? = null

        fun getDatabase(context: Context): MedAlertDatabase =
            INSTANCE ?: synchronized(this) {
                Room
                    .databaseBuilder(context, MedAlertDatabase::class.java, "med_alert_database")
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
