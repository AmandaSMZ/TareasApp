package es.tierno.amanda.mz.tareasapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.ObtenerNotaUseCase

class NotasViewModelFactory(private val obtenerNotaUseCase: ObtenerNotaUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotasViewModel(obtenerNotaUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}