package com.example.medalert.ui.home

import com.example.medalert.data.MedAlertRepository
import com.example.medalert.data.Reminder
import com.example.medalert.rules.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // 1. Mocks
    private val medAlertRepository: MedAlertRepository = mockk()

    // 2. ViewModel bajo prueba
    private lateinit var viewModel: HomeViewModel

    @Test
    fun `reminderUiState expone la lista de recordatorios del repositorio`() =
        runTest {
            // GIVEN: Preparamos datos de prueba
            val testReminders =
                listOf(
                    Reminder(
                        id = 1,
                        nombreMedicamento = "Aspirina",
                        descripcion = "Para el dolor",
                        dosis = "100mg",
                        formaConsumo = "Oral",
                        imagenUri = null,
                    ),
                    Reminder(
                        id = 2,
                        nombreMedicamento = "Vitamina C",
                        descripcion = "Para la salud",
                        dosis = "500mg",
                        formaConsumo = "Capsula",
                        imagenUri = null,
                    ),
                )

            // Entrenamos al mock: Cuando pidan todos los recordatorios, devuelve un Flow con nuestra lista
            every { medAlertRepository.getAllReminders() } returns flowOf(testReminders)

            // Inicializamos el ViewModel
            viewModel = HomeViewModel(medAlertRepository)

            // WHEN: Activamos el flujo
            // Creamos una corrutina en segundo plano para "escuchar" y mantener vivo el flujo durante el test.
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel.reminderUiState.collect {}
            }

            // THEN: Verificamos el valor actual del StateFlow
            assertEquals(testReminders, viewModel.reminderUiState.value)
        }

    @Test
    fun `reminderUiState inicia con lista vacia si el repositorio aun no emite`() =
        runTest {
            // GIVEN: Un repositorio que devuelve un flujo vacío o pendiente (simulado aquí con lista vacía inicial)
            every { medAlertRepository.getAllReminders() } returns flowOf(emptyList())

            // WHEN: Inicializamos
            viewModel = HomeViewModel(medAlertRepository)

            // Activamos el flujo
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel.reminderUiState.collect {}
            }

            // THEN: Debería ser una lista vacía
            assertEquals(emptyList<Reminder>(), viewModel.reminderUiState.value)
        }
}
