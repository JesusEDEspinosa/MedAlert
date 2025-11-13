package com.example.medalert.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class Reminder (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombreMedicamento: String,
    val descripcion: String,
    val dosis: String?,
    val formaConsumo: String?,
    val imagenUri: String?,
    val activo: Boolean = true,
    val fechaCreado: Long = System.currentTimeMillis()

)