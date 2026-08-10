package com.example.carnotaturismo.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.carnotaturismo.R
import com.example.carnotaturismo.databinding.FragmentAjustesBinding
import com.example.carnotaturismo.services.MusicService
import com.example.carnotaturismo.viewModel.TurismoAppModel

/**
 * Clase que representa el fragmento Ajustes
 */
class AjustesFragment : Fragment() {
    // bindings
    private var _binding: FragmentAjustesBinding? = null
    val binding get() = _binding!!

    // modelos
    private val model: TurismoAppModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        val view = binding.root

        // Spinner idiomas (cargado desde recursos para i18n)
        val idiomas = resources.getStringArray(R.array.idiomas)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, idiomas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIdioma.adapter = adapter

        // Observar cambios en LiveData
        model.idioma.observe(viewLifecycleOwner) { valor ->
            val pos = idiomas.indexOf(valor).takeIf { it >= 0 } ?: 0
            binding.spinnerIdioma.setSelection(pos)
        }

        // Observa cambios en la preferencia de música y arranca/para el servicio
        model.musica.observe(viewLifecycleOwner) { activo ->
            binding.switchMusica.isChecked = activo
            val intent = Intent(requireContext(), MusicService::class.java)
            if (activo) {
                requireContext().startService(intent)
            } else {
                requireContext().stopService(intent)
            }
        }

        // Descomentar si que quiere que el ajuste de musica se cambie de forma independiente al boton de guardar
        // binding.switchMusica.setOnCheckedChangeListener { _, isChecked -> model.setMusica(isChecked) }

        // Guardar ajustes (idioma + música)
        binding.botonGuardar.setOnClickListener {
            val seleccionado = binding.spinnerIdioma.selectedItem.toString()
            val musica = binding.switchMusica.isChecked
            model.setIdioma(seleccionado)
            model.setMusica(musica)
            Toast.makeText(requireContext(), getString(R.string.ajustes_guardados), Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
