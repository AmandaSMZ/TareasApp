package es.tierno.amanda.mz.tareasapp.ui.presentacion

import TareaViewModel
import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import es.tierno.amanda.mz.tareasapp.databinding.FragmentListaTareasBinding
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.ObtenerTareasUseCase
import es.tierno.amanda.mz.tareasapp.dominio.modelo.TareasPorPrioridad

class ListaTareasFragment : Fragment(){
/*
    private lateinit var binding: FragmentListaTareasBinding
    private val viewModel: TareaViewModel by viewModels()
    private lateinit var adapter: ArrayAdapter<String>
    lateinit var getPersonasCaseUse : ObtenerTareasUseCase

    companion object {
        fun newInstance() = ListaTareasFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaTareasBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSiguiente.setOnClickListener(this)

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mutableListOf())
        binding.lista.adapter = adapter
        viewModel.leerPrioridad()

        viewModel.prioridad.observe(viewLifecycleOwner, Observer { tareasPorPrioridad : TareasPorPrioridad ->
            binding.txtPrioridad.text = tareasPorPrioridad.nombre
            val tareaNombres = tareasPorPrioridad.tareas.map { it.titulo }
            adapter.clear()
            adapter.addAll(tareaNombres)
            adapter.notifyDataSetChanged()
        })
    }
    override fun onClick(v: View?) {
        //Cargo otra persona en el viewModel
        viewModel.leerPrioridad()
    }
 */
}