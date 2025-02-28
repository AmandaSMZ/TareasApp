package es.tierno.amanda.mz.tareasapp.dominio.casodeuso

import es.tierno.amanda.mz.tareasapp.data.TareaRepository
import javax.inject.Inject

class EliminarTareasUseCase @Inject constructor (private val repositorio: TareaRepository) {
    suspend operator fun invoke() = repositorio.eliminarTareas()
}