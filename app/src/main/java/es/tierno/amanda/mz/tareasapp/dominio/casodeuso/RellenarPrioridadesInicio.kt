package es.tierno.amanda.mz.tareasapp.dominio.casodeuso

import es.tierno.amanda.mz.tareasapp.data.TareaRepository
import es.tierno.amanda.mz.tareasapp.data.room.entidades.PrioridadEntity
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import javax.inject.Inject

class RellenarPrioridadesInicio @Inject constructor (private val repositorio: TareaRepository) {
    suspend operator fun invoke(prioridad: PrioridadEntity) = repositorio.insertarPrioridad(prioridad)
}