package es.tierno.amanda.mz.tareasapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import es.tierno.amanda.mz.tareasapp.data.room.dao.PrioridadDao
import es.tierno.amanda.mz.tareasapp.data.room.dao.TareaDao
import es.tierno.amanda.mz.tareasapp.data.room.entidades.PrioridadEntity
import es.tierno.amanda.mz.tareasapp.data.room.entidades.TareaEntity

@Database(entities = [TareaEntity::class, PrioridadEntity::class], version = 1)
abstract class TareaDatabase : RoomDatabase() {
    abstract fun prioridadDao(): PrioridadDao
    abstract fun tareaDao(): TareaDao
}