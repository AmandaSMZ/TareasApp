import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.tierno.amanda.mz.tareasapp.data.TareaRepository
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.ObtenerTareasUseCase
import es.tierno.amanda.mz.tareasapp.dominio.modelo.TareasPorPrioridad
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class TareaViewModel //@Inject constructor
    ()
    /*
    private val obtenerTareasUseCase : ObtenerTareasUseCase) : ViewModel() {
    val prioridad = MutableLiveData<TareasPorPrioridad>()

    fun leerPrioridad() {
        viewModelScope.launch {
            val resul = obtenerTareasUseCase.invoke()
            prioridad.postValue(resul)
        }
    }

}*/