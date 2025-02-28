package es.tierno.amanda.mz.tareasapp.data.room.entidades

import androidx.room.Embedded
import androidx.room.Relation

data class TareasPrioridad (
    @Embedded val prioridad : PrioridadEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "prioridad"
    )
    val tareas: List<TareaEntity>


)