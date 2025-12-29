package com.example.carnotaturismo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.carnotaturismo.databinding.FragmentAjustesBinding
import com.example.carnotaturismo.model.TurismoAppModel

/**
 * Clase que representa el fragmento Ajustes
 */
class AjustesFragment : Fragment() {
    // bindings
    private var _binding: FragmentAjustesBinding? = null
    private val binding get() = _binding!!

    // modelos
    private val model: TurismoAppModel by viewModels() {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        val view = binding.root

        // Spinner idiomas
        val idiomas = listOf("Sistema", "Español", "Galego", "English")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, idiomas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIdioma.adapter = adapter

        // Observar cambios en LiveData
        model.idioma.observe(viewLifecycleOwner) { valor ->
            val pos = idiomas.indexOf(valor).takeIf { it >= 0 } ?: 0
            binding.spinnerIdioma.setSelection(pos)
        }

        model.musica.observe(viewLifecycleOwner) { activo ->
            binding.switchMusica.isChecked = activo
        }

        // Guardar
        binding.botonGuardar.setOnClickListener {
            val seleccionado = binding.spinnerIdioma.selectedItem.toString()
            val musica = binding.switchMusica.isChecked
            model.setIdioma(seleccionado)
            model.setMusica(musica)
            Toast.makeText(requireContext(), "Ajustes guardados", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
