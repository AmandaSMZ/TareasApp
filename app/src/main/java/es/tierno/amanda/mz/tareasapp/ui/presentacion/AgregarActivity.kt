package es.tierno.amanda.mz.tareasapp.ui.presentacion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.tierno.amanda.mz.tareasapp.R
import es.tierno.amanda.mz.tareasapp.databinding.ActivityAgregarBinding
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.InsertarTareaUseCase
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AgregarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgregarBinding
    @Inject
    lateinit var insertarTareaUseCase: InsertarTareaUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.edtTitulo.text.toString()
            val descripcion = binding.edtDescripcion.text.toString()
            val id_prioridad = binding.idPrioridad.selectedItem.toString()
            CoroutineScope(Dispatchers.IO).launch{
                insertarTareaUseCase.invoke(Tarea(nombre,descripcion,id_prioridad.toInt()))
            }
        }
    }
}