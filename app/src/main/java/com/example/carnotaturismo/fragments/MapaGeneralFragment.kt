package com.example.carnotaturismo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.carnotaturismo.databinding.FragmentMapaGeneralBinding
import com.example.carnotaturismo.viewModel.TurismoAppModel
import kotlin.getValue

/**
 * Clase que representa el fragmento MapaGeneral
 */
class MapaGeneralFragment : Fragment() {
    private var _binding: FragmentMapaGeneralBinding? = null
    private val binding get() = _binding!!

    // modelo
    private val model: TurismoAppModel by viewModels() {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapaGeneralBinding.inflate(inflater, container, false)
        val view = binding.root

        // Lista de lugares
        var lugares = model.lugares.value ?: emptyList()

        // Declaramos el listado
        binding.tituloUbicaciones.text = lugares.joinToString(separator = "\n") { it.titulo }

        // Observamos cambios
        model.lugares.observe(viewLifecycleOwner) { lista ->
            binding.tituloUbicaciones.text = lista.joinToString(separator = "\n") { it.titulo }
        }

        //val ctx = holder.itemView.context
        //val imgRef = currentItem.imagen

        // extrae "drawable" y "mi_imagen"
        //val (type, name) = imgRef.removePrefix("@").split('/', limit = 2)
        //val resId = ctx.resources.getIdentifier(name, type, ctx.packageName)
        //if (resId != 0) {
        //    holder.imagen.setImageResource(resId)
        //} else {
        //    holder.imagen.setImageResource(R.drawable.error_imagen) // fallback
        //}

        return view

    }


}