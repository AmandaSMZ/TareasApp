package es.tierno.amanda.mz.tareasapp.dominio.modelo

import es.tierno.amanda.mz.tareasapp.data.modelo.TareaModel

data class TareasPorPrioridad(val nombre:String, val tareas : List<Tarea>)
