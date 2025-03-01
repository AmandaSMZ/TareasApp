package es.tierno.amanda.mz.tareasapp.data

import es.tierno.amanda.mz.tareasapp.data.mock.TareaProvider
import es.tierno.amanda.mz.tareasapp.data.modelo.TareaConPrioridadModel
import es.tierno.amanda.mz.tareasapp.data.modelo.TareaModel
import es.tierno.amanda.mz.tareasapp.data.room.dao.PrioridadDao
import es.tierno.amanda.mz.tareasapp.data.room.dao.TareaDao
import es.tierno.amanda.mz.tareasapp.data.room.entidades.PrioridadEntity
import es.tierno.amanda.mz.tareasapp.data.room.entidades.TareaEntity
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import javax.inject.Inject

class TareaRepository @Inject constructor(
    private val tareaDao: TareaDao,
    private val prioridadDao: PrioridadDao,
    private val mock: TareaProvider) {

    suspend fun insertarTarea(tarea: TareaModel) {
        var nuevaTarea = TareaEntity(0,tarea.titulo,tarea.descripcion,tarea.prioridadId)
        tareaDao.insert(nuevaTarea)
    }
    suspend fun insertarPrioridad(prioridad: PrioridadEntity){
        prioridadDao.insert(prioridad)
    }
    suspend fun eliminarPrioridades(){
        prioridadDao.borrarTabla()
    }
    suspend fun eliminarTareas(){
        tareaDao.borrarTabla()
    }

    suspend fun obtenerListaTareasCompleta(): List<TareaConPrioridadModel>{
        val lista = tareaDao.obtenerListaTareas()
        return lista.map { tareaConPrioridad ->
            TareaConPrioridadModel(
                titulo = tareaConPrioridad.tarea.titulo,
                descripcion = tareaConPrioridad.tarea.descripcion,
                prioridad = tareaConPrioridad.prioridad.nombre
            )
        }
    }
}