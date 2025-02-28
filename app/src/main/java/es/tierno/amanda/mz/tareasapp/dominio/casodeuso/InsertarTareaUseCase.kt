package es.tierno.amanda.mz.tareasapp.dominio.casodeuso

import es.tierno.amanda.mz.tareasapp.data.TareaRepository
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import javax.inject.Inject

class InsertarTareaUseCase @Inject constructor (private val repositorio: TareaRepository) {
    suspend operator fun invoke(tarea: Tarea) = repositorio.insertarTarea(tarea)
}