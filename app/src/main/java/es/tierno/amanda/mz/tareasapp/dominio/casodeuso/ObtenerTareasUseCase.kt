package es.tierno.amanda.mz.tareasapp.dominio.casodeuso

import android.content.Context
import androidx.lifecycle.lifecycleScope
import es.tierno.amanda.mz.tareasapp.data.TareaRepository
import es.tierno.amanda.mz.tareasapp.dominio.modelo.TareasPorPrioridad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ObtenerTareasUseCase @Inject constructor(private val repositorio: TareaRepository) {
    private lateinit var listaId : List<Int>
    private var posicion : Int = 0
    init{
        CoroutineScope(Dispatchers.IO).launch {
            getAllPrioridades()
        }
    }

    suspend fun getAllPrioridades() {
        listaId = repositorio.getAllPrioridades()
    }

    suspend operator fun invoke() :  TareasPorPrioridad{
        val id = listaId[posicion]
        val resul = repositorio.leerPrioridad(id)
        if (posicion < listaId.size-1){
            posicion++
        }else{
            posicion = 0
        }
        return resul.toTareasPorPrioridad()
    }
}