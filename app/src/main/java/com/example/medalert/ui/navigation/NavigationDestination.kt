package com.example.medalert.ui.navigation

interface NavigationDestination {
    val route: String
    val titleRes: String
}

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = "Reminders"
}

object ReminderEntryDestination : NavigationDestination {
    override val route = "reminder_entry"
    override val titleRes = "add Reminder"
}