package es.tierno.amanda.mz.tareasapp.ui.presentacion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import es.tierno.amanda.mz.tareasapp.databinding.FragmentNotasBinding
import es.tierno.amanda.mz.tareasapp.ui.viewmodel.NotasViewModel
import es.tierno.amanda.mz.tareasapp.dominio.casodeuso.ObtenerNotaUseCase
import es.tierno.amanda.mz.tareasapp.data.NotasRepository
import es.tierno.amanda.mz.tareasapp.ui.viewmodel.NotasViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotasFragment : Fragment(), OnClickListener {

    private var _binding: FragmentNotasBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotasViewModel by viewModels() {
        NotasViewModelFactory(ObtenerNotaUseCase(NotasRepository()))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observa los cambios en el LiveData del ViewModel
        viewModel.nota.observe(viewLifecycleOwner, Observer { nota ->
            if (nota != null) {
                binding.txtNota.text = nota.nota
                binding.txtAutor.text = nota.autor
            }
        })

        binding.btnActualizar.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        viewModel.fetchNota()
    }

}