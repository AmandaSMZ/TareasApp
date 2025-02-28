package es.tierno.amanda.mz.tareasapp.data

import es.tierno.amanda.mz.tareasapp.data.mock.TareaProvider
import es.tierno.amanda.mz.tareasapp.data.modelo.PrioridadTareaModel
import es.tierno.amanda.mz.tareasapp.data.modelo.TareaModel
import es.tierno.amanda.mz.tareasapp.data.room.dao.PrioridadDao
import es.tierno.amanda.mz.tareasapp.data.room.dao.TareaDao
import es.tierno.amanda.mz.tareasapp.data.room.entidades.PrioridadEntity
import es.tierno.amanda.mz.tareasapp.data.room.entidades.TareaEntity
import es.tierno.amanda.mz.tareasapp.data.room.entidades.TareasPrioridad
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import javax.inject.Inject

class TareaRepository @Inject constructor(
    private val tareaDao: TareaDao,
    private val prioridadDao: PrioridadDao,
    private val mock: TareaProvider) {

    fun getAllPrioridades(): List<Int> {
        return prioridadDao.getAllId()
    }

    fun leerPrioridad(prioridadId: Int): PrioridadTareaModel {
        val res = prioridadDao.getTareasPorPrioridad(prioridadId)
        return mapearTareasPrioridadAModelo(res)

    }
    suspend fun insertarTarea(tarea: Tarea) {
        var nuevaTarea = TareaEntity(0,tarea.titulo,tarea.descripcion,tarea.idPrioridad)
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

    fun mapearTareasPrioridadAModelo(tareasPrioridad: TareasPrioridad): PrioridadTareaModel {
        val tareasModel = tareasPrioridad.tareas.map { tareaEntity ->
            TareaModel(
                titulo = tareaEntity.titulo,
                descripcion = tareaEntity.descripcion,
                prioridadId = tareaEntity.prioridad
            )
        }
        return PrioridadTareaModel(
            id = tareasPrioridad.prioridad.id,
            nombre = tareasPrioridad.prioridad.nombre,
            tareas = tareasModel
        )
    }

}