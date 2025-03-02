package es.tierno.amanda.mz.tareasapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.tierno.amanda.mz.tareasapp.data.room.entidades.TareaConPrioridad
import es.tierno.amanda.mz.tareasapp.data.room.entidades.TareaEntity
@Dao
interface TareaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tarea: TareaEntity)

    @Query("DELETE FROM Tarea")
    suspend fun borrarTabla()

    @Query("SELECT * FROM Tarea ORDER BY prioridad ASC")
    suspend fun obtenerLista() : List<TareaConPrioridad>
}