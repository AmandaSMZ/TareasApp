package es.tierno.amanda.mz.tareasapp.data.room.entidades

import androidx.room.Embedded
import androidx.room.Relation

data class TareaConPrioridad(
    @Embedded val tarea : TareaEntity,
    @Relation(
        parentColumn = "prioridad",
        entityColumn = "id"
    )
    val prioridad: PrioridadEntity)
