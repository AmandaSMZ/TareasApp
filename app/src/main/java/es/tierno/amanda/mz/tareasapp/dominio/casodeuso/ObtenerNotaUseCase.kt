package es.tierno.amanda.mz.tareasapp.dominio.casodeuso

import es.tierno.amanda.mz.tareasapp.data.NotasRepository
import es.tierno.amanda.mz.tareasapp.data.servicioRest.NotasRespuesta
import javax.inject.Inject

class ObtenerNotaUseCase @Inject constructor(private val repository: NotasRepository) {
    suspend operator fun invoke(): NotasRespuesta? {
        return repository.getNota()?.get(0)
    }
}