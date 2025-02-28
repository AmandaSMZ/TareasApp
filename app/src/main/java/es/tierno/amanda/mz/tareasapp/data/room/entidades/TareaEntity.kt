package es.tierno.amanda.mz.tareasapp.data.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Tarea")
data class TareaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val prioridad: Int)