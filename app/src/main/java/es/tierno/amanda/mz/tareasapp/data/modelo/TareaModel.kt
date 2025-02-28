package es.tierno.amanda.mz.tareasapp.data.modelo

import es.tierno.amanda.mz.tareasapp.data.room.entidades.TareasPrioridad
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea

data class TareaModel (val titulo: String, val descripcion: String, val prioridadId: Int){

    public fun toTarea(): Tarea{
        return Tarea(titulo,descripcion, prioridadId)
    }

}
