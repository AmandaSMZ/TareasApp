package es.tierno.amanda.mz.tareasapp.data.room.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Prioridad")
data class PrioridadEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nombre: String)