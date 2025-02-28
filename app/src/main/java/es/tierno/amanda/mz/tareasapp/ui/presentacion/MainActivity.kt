package es.tierno.amanda.mz.tareasapp.ui.presentacion

import TareaViewModel
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.tierno.amanda.mz.tareasapp.R
import es.tierno.amanda.mz.tareasapp.data.TareaRepository
import es.tierno.amanda.mz.tareasapp.data.room.entidades.PrioridadEntity
import es.tierno.amanda.mz.tareasapp.databinding.ActivityMainBinding
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.EliminarPrioridadesUseCase
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.EliminarTareasUseCase
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.InsertarTareaUseCase
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.ObtenerTareasUseCase
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.RellenarPrioridadesInicio
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import es.tierno.amanda.mz.tareasapp.dominio.modelo.TareasPorPrioridad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity () : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    //private val viewModel: TareaViewModel by viewModels()
    private lateinit var adapter: ArrayAdapter<String>

    @Inject
    lateinit var insertarTareaCaseUse : InsertarTareaUseCase
    @Inject
    lateinit var rellenarPrioridadesInicio: RellenarPrioridadesInicio
    @Inject
    lateinit var eliminarPrioridadesUseCase: EliminarPrioridadesUseCase
    @Inject
    lateinit var eliminarTareasUseCase: EliminarTareasUseCase
    @Inject
    lateinit var obtenerTareasUseCase: ObtenerTareasUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        insertarPrioridades()
        insertarTareas()

        binding.button.setOnClickListener(this)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        binding.lista.adapter = adapter


}
    private fun insertarPrioridades() {
        lifecycleScope.launch(Dispatchers.IO){
            eliminarPrioridadesUseCase.invoke()
            val prioridades = listOf(
                PrioridadEntity(1, "Importante"),
                PrioridadEntity(2, "Urgente"),
                PrioridadEntity(3, "Prioridad Media"),
                PrioridadEntity(4, "Sin prisa")
            )
            prioridades.forEach { prioridad ->
                rellenarPrioridadesInicio(prioridad)
            }
        }
    }
    private fun insertarTareas() {

        lifecycleScope.launch(Dispatchers.IO) {
            eliminarTareasUseCase.invoke()
            val tareas = listOf(
                Tarea(titulo = "Revisar presupuesto", descripcion = "Analizar el presupuesto del proyecto para el próximo trimestre.", idPrioridad = 1),
                Tarea(titulo = "Preparar presentación", descripcion = "Crear diapositivas para la reunión de la junta directiva.", idPrioridad = 1),
                Tarea(titulo = "Actualizar software", descripcion = "Instalar las últimas actualizaciones de seguridad en todos los equipos.", idPrioridad = 1),
                Tarea(titulo = "Planificar evento", descripcion = "Organizar el evento anual de la empresa.", idPrioridad = 1),
                Tarea(titulo = "Responder correos", descripcion = "Responder a los correos electrónicos urgentes de los clientes.", idPrioridad = 2),
                Tarea(titulo = "Reparar servidor", descripcion = "Solucionar el problema del servidor que afecta a la producción.", idPrioridad = 2),
                Tarea(titulo = "Reunión de emergencia", descripcion = "Asistir a la reunión de emergencia con el equipo de TI.", idPrioridad = 2),
                Tarea(titulo = "Enviar informe", descripcion = "Enviar el informe financiero antes del final del día.", idPrioridad = 2),
                Tarea(titulo = "Revisar contratos", descripcion = "Revisar los contratos de los nuevos proveedores.", idPrioridad = 3),
                Tarea(titulo = "Capacitación del personal", descripcion = "Organizar una sesión de capacitación para el nuevo software.", idPrioridad = 3),
                Tarea(titulo = "Actualizar inventario", descripcion = "Actualizar el inventario en el sistema de gestión.", idPrioridad = 3),
                Tarea(titulo = "Revisar políticas", descripcion = "Revisar y actualizar las políticas de la empresa.", idPrioridad = 3),
                Tarea(titulo = "Organizar archivos", descripcion = "Organizar los archivos antiguos en el almacenamiento.", idPrioridad = 4),
                Tarea(titulo = "Leer artículos", descripcion = "Leer artículos sobre las últimas tendencias del mercado.", idPrioridad = 4),
                Tarea(titulo = "Mejorar sitio web", descripcion = "Realizar mejoras en el diseño del sitio web.", idPrioridad = 4),
                Tarea(titulo = "Planificar vacaciones", descripcion = "Planificar las vacaciones del equipo para el próximo año.", idPrioridad = 4)
            )
            tareas.forEach { tarea ->
                insertarTareaCaseUse.invoke(tarea)
            }
        }
    }

    override fun onClick(v: View?) {
        leerTareas()
    }
    private fun leerTareas(){
        lifecycleScope.launch(Dispatchers.IO) {
            var prioridad = obtenerTareasUseCase.invoke()
            withContext(Dispatchers.Main) {
                binding.txtPrioridad.text = prioridad.nombre
                val tareaNombres = prioridad.tareas.map { it.titulo }
                adapter.clear()
                adapter.addAll(tareaNombres)

            }
        }
    }
}