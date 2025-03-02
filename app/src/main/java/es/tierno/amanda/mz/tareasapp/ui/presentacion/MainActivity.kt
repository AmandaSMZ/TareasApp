package es.tierno.amanda.mz.tareasapp.ui.presentacion

import TareaAdapter
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.tierno.amanda.mz.tareasapp.databinding.ActivityMainBinding
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.EliminarPrioridadesUseCase
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.EliminarTareasUseCase
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.InsertarPrioridadUseCase
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.InsertarTareaUseCase
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.ObtenerListaTareasUseCase
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Prioridad
import es.tierno.amanda.mz.tareasapp.dominio.modelo.Tarea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity () : AppCompatActivity(), OnClickListener {

    companion object{
        const val WRITE_CALENDAR_REQUEST_CODE = 10
        const val MENSAJE_PERMISO_CONCEDIDO = "Permiso concedido"
        const val MENSAJE_CONCEDA_PERMISO_AJUSTES = "Conceda el permiso en ajustes"
        const val PREFS_NAME = "AppPrefs"
        const val PRIMERA_EJECUCION = "primeraEjecucion"
    }
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var insertarTareaCaseUse : InsertarTareaUseCase
    @Inject
    lateinit var insertarPrioridadUseCase: InsertarPrioridadUseCase
    @Inject
    lateinit var eliminarPrioridadesUseCase: EliminarPrioridadesUseCase
    @Inject
    lateinit var eliminarTareasUseCase: EliminarTareasUseCase
    @Inject
    lateinit var obtenerListaTareasUseCase: ObtenerListaTareasUseCase

    private lateinit var adapter : TareaAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkCalendarPermission()

        comprobarPrimeraEjecucion()

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        binding.btnAgregar.setOnClickListener(this)
        cargarTareas()

}

    private fun insertarPrioridades() {
        lifecycleScope.launch(Dispatchers.IO){
            eliminarPrioridadesUseCase.invoke()
            val prioridades = listOf(
                Prioridad(1, "Importante"),
                Prioridad(2, "Urgente"),
                Prioridad(3, "Prioridad Media"),
                Prioridad(4, "Sin prisa")
            )
            prioridades.forEach { prioridad ->
                insertarPrioridadUseCase(prioridad)
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
        val intent = Intent(this, AgregarActivity::class.java)
        startActivity(intent)
    }

    private fun cargarTareas() {
        CoroutineScope(Dispatchers.Main).launch {
            val listaTareas = obtenerListaTareasUseCase.invoke()
            val adaptador = TareaAdapter(listaTareas)
            recyclerView.adapter = adaptador
        }
    }

    private fun checkCalendarPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR)
            != PackageManager.PERMISSION_GRANTED) {
            requestCalendarPermission()
        } else {
            Toast.makeText(this,MENSAJE_PERMISO_CONCEDIDO, Toast.LENGTH_SHORT).show()
        }
    }
    private fun requestCalendarPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_CALENDAR)) {
            Toast.makeText(this, MENSAJE_CONCEDA_PERMISO_AJUSTES, Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_CALENDAR),
                Companion.WRITE_CALENDAR_REQUEST_CODE)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            WRITE_CALENDAR_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(
                        this,
                        MENSAJE_PERMISO_CONCEDIDO,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    //El usuario ha rechazado el permiso
                    Toast.makeText(this, MENSAJE_CONCEDA_PERMISO_AJUSTES, Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
            }
        }
    }

    private fun comprobarPrimeraEjecucion(){
        val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val esPrimeraEjecucion = sharedPreferences.getBoolean(PRIMERA_EJECUCION, true)
        if (esPrimeraEjecucion) {
            insertarPrioridades()
            insertarTareas()
            sharedPreferences.edit().putBoolean(PRIMERA_EJECUCION, false).apply()
        }
    }
}