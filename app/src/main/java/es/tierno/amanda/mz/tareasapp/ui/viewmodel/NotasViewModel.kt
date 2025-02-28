package es.tierno.amanda.mz.tareasapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.ObtenerNotaUseCase
import es.tierno.amanda.mz.tareasapp.data.servicioRest.NotasRespuesta
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotasViewModel @Inject constructor(private val obtenerNotaUseCase: ObtenerNotaUseCase) : ViewModel() {
    val nota = MutableLiveData<NotasRespuesta>()

    fun fetchNota() {
        viewModelScope.launch {
            val result = obtenerNotaUseCase()
            result?.let {
                nota.postValue(it)
            }
        }
    }
}