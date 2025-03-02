package es.tierno.amanda.mz.tareasapp.ui.presentacion

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.tierno.amanda.mz.tareasapp.databinding.ActivityAgregarBinding
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.InsertarTareaUseCase
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AgregarActivity : AppCompatActivity() {
    companion object{
        const val MENSAJE_CAMPOS_VACIOS : String = "Por favor, completa todos los campos."
    }
    private lateinit var binding: ActivityAgregarBinding

    @Inject
    lateinit var insertarTareaUseCase: InsertarTareaUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.edtTitulo.text.toString().trim()
            val descripcion = binding.edtDescripcion.text.toString().trim()
            val id_prioridad = binding.idPrioridad.selectedItem.toString().trim()

            if (nombre.isEmpty() || descripcion.isEmpty() || id_prioridad.isEmpty()) {
                Toast.makeText(this, Companion.MENSAJE_CAMPOS_VACIOS, Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    insertarTareaUseCase.invoke(Tarea(nombre, descripcion, id_prioridad.toInt()))
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}