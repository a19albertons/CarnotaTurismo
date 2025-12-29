package com.example.carnotaturismo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.carnotaturismo.databinding.FragmentItinerarioDetallesBinding
import com.example.carnotaturismo.viewModel.TurismoAppModel

/**
 * Clase que representa el fragmento Itinerario Detalles
 */
class ItinerarioDetallesFragment : Fragment() {
    // bindings
    private var _binding: FragmentItinerarioDetallesBinding? = null
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
        _binding = FragmentItinerarioDetallesBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recuperar Bundle
        val args = ItinerarioDetallesFragmentArgs.fromBundle(requireArguments())
        val ruta = args.ruta

        // Actualizar UI
        binding.titulo.text = ruta.titulo
        // Cargar imagen desde la referencia almacenada en la BD (p. ej. "@drawable/nombre")
        com.example.carnotaturismo.util.ImageHelper.setImageFromRef(requireContext(), ruta.imagen, binding.fotoMapa)
        binding.leyenda.text = ruta.leyenda

        // Obtener los lugares asociados a esta ruta y mostrarlos
        model.getRutaConLugares(ruta.id).observe(viewLifecycleOwner) { rutaConLugares ->
            val textoLugares = rutaConLugares?.lugares?.joinToString(separator = "\n") { it.titulo } ?: ""
            binding.lugaresVisitar.text = textoLugares
        }

        binding.descripcion.text = ruta.descripcion
        binding.importante.text = ruta.importante

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}