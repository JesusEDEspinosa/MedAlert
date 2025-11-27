package com.example.medalert.ui.reminder

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medalert.data.MedAlertRepository
import com.example.medalert.data.Reminder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReminderEntryViewModel(
    private val medAlertRepository: MedAlertRepository,
) : ViewModel() {
    private val _reminderUiState = MutableStateFlow(ReminderUiState())
    val reminderUiState: StateFlow<ReminderUiState> = _reminderUiState.asStateFlow()

    fun updateUiState(newState: ReminderUiState) {
        _reminderUiState.value = newState
    }

    fun saveRaminder() {
        viewModelScope.launch {
            val reminder =
                Reminder(
                    id = reminderUiState.value.id,
                    nombreMedicamento = reminderUiState.value.nombreMedicamento,
                    descripcion = reminderUiState.value.descripcion,
                    dosis = reminderUiState.value.dosis,
                    formaConsumo = reminderUiState.value.formaConsumo,
                    imagenUri = reminderUiState.value.imagenUri,
                    activo = reminderUiState.value.activo,
                    fechaCreado = reminderUiState.value.fechaCreado,
                )

            medAlertRepository.insertReminder(reminder)
        }
    }
}

data class ReminderUiState(
    val id: Int = 0,
    val nombreMedicamento: String = "",
    val descripcion: String = "",
    val dosis: String = "Tabletas",
    val formaConsumo: String = "Oral",
    val activo: Boolean = true,
    val imagenUri: String? = null,
    val fechaCreado: Long = System.currentTimeMillis(),
)
