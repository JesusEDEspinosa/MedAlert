package com.example.medalert.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medalert.MedAlertApplication
import com.example.medalert.ui.home.HomeViewModel
import com.example.medalert.ui.reminder.ReminderEntryViewModel

object HomeViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MedAlertApplication
            val repository = application.container.medAlertRepository
            HomeViewModel(repository)
        }
    }
}

object ReminderEntryViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MedAlertApplication
            val repository = application.container.medAlertRepository
            ReminderEntryViewModel(repository)
        }
    }
}