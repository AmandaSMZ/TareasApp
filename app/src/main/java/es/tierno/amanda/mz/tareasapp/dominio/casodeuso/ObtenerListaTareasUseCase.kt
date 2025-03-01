package es.tierno.amanda.mz.tareasapp.dominio.casodeuso

import es.tierno.amanda.mz.tareasapp.data.TareaRepository
import es.tierno.amanda.mz.tareasapp.data.modelo.TareaConPrioridadModel
import es.tierno.amanda.mz.tareasapp.dominio.modelo.TareaCompleta
import javax.inject.Inject

class ObtenerListaTareasUseCase @Inject constructor( val repository: TareaRepository) {
    suspend operator fun invoke(): List<TareaCompleta> {
        val lista = repository.obtenerListaTareasCompleta()
        val resultado : MutableList<TareaCompleta> = mutableListOf()
        for (tarea : TareaConPrioridadModel in lista){
            resultado.add(tarea.toTareaCompleta())
        }
        return resultado
    }
}