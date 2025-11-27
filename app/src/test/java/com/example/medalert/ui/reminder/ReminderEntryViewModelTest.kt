package com.example.medalert.ui.reminder

import com.example.medalert.data.MedAlertRepository
import com.example.medalert.data.Reminder
import com.example.medalert.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ReminderEntryViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // 1. Mock del Repositorio
    private val medAlertRepository: MedAlertRepository = mockk()

    // 2. ViewModel a probar
    private lateinit var viewModel: ReminderEntryViewModel

    @Before
    fun setup() {
        viewModel = ReminderEntryViewModel(medAlertRepository)
    }

    @Test
    fun `updateUiState actualiza el estado correctamente`() {
        // GIVEN: Un nuevo estado
        val newState =
            ReminderUiState(
                nombreMedicamento = "Paracetamol",
                dosis = "500mg",
                activo = true,
            )

        // WHEN: Llamamos a updateUiState
        viewModel.updateUiState(newState)

        // THEN: El estado del ViewModel debe coincidir
        assertEquals(newState, viewModel.reminderUiState.value)
    }

    @Test
    fun `saveRaminder inserta el recordatorio en el repositorio con los datos del estado`() =
        runTest {
            // GIVEN:
            // 1. Configuramos el estado del ViewModel con datos de prueba
            val testState =
                ReminderUiState(
                    id = 0,
                    nombreMedicamento = "Ibuprofeno",
                    descripcion = "Para el dolor",
                    dosis = "400mg",
                    formaConsumo = "Oral",
                    activo = true,
                    fechaCreado = 123456789L,
                )
            viewModel.updateUiState(testState)

            // 2. Entrenamos al Mock: Cuando le pidan insertar, que no haga nada (just runs)
            coEvery { medAlertRepository.insertReminder(any()) } returns 1L

            // WHEN: Ejecutamos la función a probar
            viewModel.saveRaminder()

            // THEN: Verificamos que el repositorio recibió la orden correcta

            // Capturamos el objeto Reminder que se pasó al repositorio
            val reminderSlot = slot<Reminder>()
            coVerify { medAlertRepository.insertReminder(capture(reminderSlot)) }

            // Comprobamos que los datos capturados sean los mismos que pusimos en el estado
            val capturedReminder = reminderSlot.captured
            assertEquals("Ibuprofeno", capturedReminder.nombreMedicamento)
            assertEquals("400mg", capturedReminder.dosis)
            assertEquals("Oral", capturedReminder.formaConsumo)
        }
}
