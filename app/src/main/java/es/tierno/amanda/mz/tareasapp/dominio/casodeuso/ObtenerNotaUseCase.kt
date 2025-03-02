package es.tierno.amanda.mz.tareasapp.dominio.casodeuso

import es.tierno.amanda.mz.tareasapp.data.NotasRepository
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Nota
import javax.inject.Inject

class ObtenerNotaUseCase @Inject constructor(private val repository: NotasRepository) {
    suspend operator fun invoke(): Nota {
        val nota = repository.getNota()
        return Nota(nota.nota,nota.autor)
    }
}