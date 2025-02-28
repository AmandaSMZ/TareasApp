package es.tierno.amanda.mz.tareasapp.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import es.tierno.amanda.mz.tareasapp.data.room.entidades.PrioridadEntity
import es.tierno.amanda.mz.tareasapp.data.room.entidades.TareasPrioridad
@Dao
interface PrioridadDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prioridad: PrioridadEntity)

    @Query("SELECT id FROM Prioridad")
    fun getAllId(): List<Int>

    @Transaction
    @Query("SELECT * FROM Prioridad WHERE id = :prioridadId")
    fun getTareasPorPrioridad(prioridadId: Int): TareasPrioridad

    @Query("DELETE FROM Prioridad")
    fun borrarTabla()
}