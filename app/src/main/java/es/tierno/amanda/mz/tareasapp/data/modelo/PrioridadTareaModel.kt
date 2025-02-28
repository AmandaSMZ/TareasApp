package es.tierno.amanda.mz.tareasapp.data.modelo

import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import es.tierno.amanda.mz.tareasapp.dominio.modelo.TareasPorPrioridad

data class PrioridadTareaModel(val id:Int, val nombre:String, val tareas : List<TareaModel>){
    fun toTareasPorPrioridad(): TareasPorPrioridad {

        val tareasConvertidas = tareas.map { tareaModel ->
            Tarea(titulo = tareaModel.titulo, descripcion = tareaModel.descripcion, idPrioridad = tareaModel.prioridadId)
        }
        return TareasPorPrioridad(nombre = this.nombre, tareas = tareasConvertidas)
    }
}
