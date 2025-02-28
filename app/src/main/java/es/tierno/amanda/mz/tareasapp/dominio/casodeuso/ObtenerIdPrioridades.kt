package es.tierno.amanda.mz.tareasapp.dominio.casodeuso

import android.content.Context
import es.tierno.amanda.mz.tareasapp.data.TareaRepository
import javax.inject.Inject


class ObtenerIdPrioridades @Inject constructor(private val repositorio: TareaRepository) {
    suspend operator fun invoke(id : Int) = repositorio.getAllPrioridades()
}