package com.example.medalert.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.medalert.data.Reminder
import com.example.medalert.ui.HomeViewModelProvider
import com.example.medalert.ui.navigation.ReminderEntryDestination
import com.example.medalert.ui.theme.AppTypography

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModelProvider.Factory),
) {
    val reminders by viewModel.reminderUiState.collectAsState()

    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ReminderEntryDestination.route) },
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar recordatorio")
            }
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
        ) {
            SearchBar()
            ReminderList(reminders)
        }
    }
}

@Composable
fun TopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .statusBarsPadding()
                .fillMaxWidth(),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            Text("MedAlert", style = AppTypography.titleLarge)
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = { },
        placeholder = { Text("Buscar") },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
    )
}

@Composable
fun ReminderList(
    reminders: List<Reminder>,
    modifier: Modifier = Modifier,
) {
    if (reminders.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay recordatorios")
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(16.dp),
        ) {
            items(reminders) { reminder ->
                ReminderCard(reminder, modifier)
            }
        }
    }
}

@Composable
fun ReminderCard(
    reminder: Reminder,
    modifier: Modifier = Modifier,
) {
    val reminder = reminder
    Card(
        modifier =
            Modifier
                .fillMaxWidth(),
        border = BorderStroke(1.dp, Color.LightGray),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(reminder.nombreMedicamento, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(reminder.descripcion, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
