package es.tierno.amanda.mz.tareasapp.data.mock

import es.tierno.amanda.mz.tareasapp.data.modelo.TareaModel
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import javax.inject.Inject

class TareaProvider @Inject constructor () {

    companion object {
        var pos = 0
        private val tareas = mutableListOf(
            Tarea("Comprar", "Comprar pan y tomate",45),
            Tarea("Cita dentista", "Jueves 25. Pagar 400",2)
        )
    }

    /*


    suspend fun leer(): Tarea {
        val persona = tareas[pos]
        if (pos < tareas.size){
            pos += 1
        }else{
            pos = 0
        }

        return Tarea(persona.title, persona.description)
    }
     */
    suspend fun leer(): MutableList<Tarea> {
        return tareas
    }

    suspend fun insertar(tarea: Tarea) {
        tareas.add(tarea)
    }
}