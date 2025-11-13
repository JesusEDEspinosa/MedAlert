package com.example.medalert.ui.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medalert.ui.ReminderEntryViewModelProvider

@Composable
fun ReminderEntryScreen (
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit = navigateBack,
    viewModel: ReminderEntryViewModel = viewModel(factory = ReminderEntryViewModelProvider.Factory)
)
{
    val reminderUiState by viewModel.reminderUiState.collectAsState()
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopBar( onNavigateUp)
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Guardar") },
                    icon = { Icon(Icons.Default.Check, contentDescription = "Guardar") },
                    onClick = {
                        viewModel.saveRaminder()
                        navigateBack()
                    }
                )
            }
        ) { innerPadding ->
            NoteEntryForm(
                reminderUiState,
                onValueChange = viewModel::updateUiState,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onNavigateUp: () -> Unit
) {
    TopAppBar(
        title = { Text( "MedAlert")  },
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        },
        modifier = Modifier
            .statusBarsPadding()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEntryForm (
    reminderUiState: ReminderUiState,
    onValueChange: (ReminderUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NameCard(
            reminderUiState.nombreMedicamento,
            onValueChange,
            reminderUiState,
            "Nombre del medicamento",
            "Escribe el nombre del medicamento",
            modifier = Modifier
        )

        DescriptionCard(
            reminderUiState.descripcion,
            onValueChange,
            reminderUiState,
            "Descripción",
            "Escribe tu descripción",
        )
    }
}


@Composable
fun NameCard(
    value: String,
    onValueChange: (ReminderUiState) -> Unit,
    reminderUiState: ReminderUiState,
    text: String,
    placeholder: String,
    modifier: Modifier = Modifier
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {

        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(16.dp, 16.dp, 0.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = value,
                onValueChange = { onValueChange(reminderUiState.copy(nombreMedicamento = it)) },
                placeholder = { Text(placeholder) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                )
            )
        }
    }
}

@Composable
fun DescriptionCard(
    value: String,
    onValueChange: (ReminderUiState) -> Unit,
    reminderUiState: ReminderUiState,
    text: String,
    placeholder: String,
    modifier: Modifier = Modifier
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {

        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(16.dp, 16.dp, 0.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = value,
                onValueChange = { onValueChange(reminderUiState.copy(descripcion = it)) },
                placeholder = { Text(placeholder) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                )
            )
        }
    }
}

