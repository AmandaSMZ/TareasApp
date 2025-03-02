package es.tierno.amanda.mz.tareasapp.dominio.casodeuso

import es.tierno.amanda.mz.tareasapp.data.TareaRepository
import es.tierno.amanda.mz.tareasapp.data.modelo.PrioridadModel
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Prioridad
import javax.inject.Inject

class InsertarPrioridadUseCase @Inject constructor (private val repositorio: TareaRepository) {
    suspend operator fun invoke(prioridad: Prioridad) {
        val prioridadModel = PrioridadModel(prioridad.id, prioridad.nombre)
        repositorio.insertarPrioridad(prioridadModel)
    }
}