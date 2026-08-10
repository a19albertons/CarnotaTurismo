package com.example.carnotaturismo.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.carnotaturismo.R
import com.example.carnotaturismo.databinding.FragmentItinerarioDetallesBinding
import com.example.carnotaturismo.util.resolveString
import com.example.carnotaturismo.viewModel.TurismoAppModel

/**
 * Clase que representa el fragmento Itinerario Detalles
 */
class ItinerarioDetallesFragment : Fragment() {
    // bindings
    private var _binding: FragmentItinerarioDetallesBinding? = null
    val binding get() = _binding!!

    // modelo
    private val model: TurismoAppModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItinerarioDetallesBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recuperar Bundle
        val args = ItinerarioDetallesFragmentArgs.fromBundle(requireArguments())
        val ruta = args.ruta

        // Actualizar UI
        binding.titulo.text = requireContext().resolveString(ruta.titulo)
        // Mostrar icono favorito segun BD
        binding.ivFavorito.setImageResource(if (ruta.favorito) R.drawable.favorito else R.drawable.no_favorito)

        // Toggle favorito al pulsar (optimistic UI + persistir via ViewModel)
        binding.ivFavorito.setOnClickListener {
            // Invertir estado
            val nuevo = !ruta.favorito
            ruta.favorito = nuevo
            // Actualizar icono
            binding.ivFavorito.setImageResource(if (nuevo) R.drawable.favorito else R.drawable.no_favorito)
            // Persistir cambio via ViewModel
            model.setFavoritoRuta(ruta.id, if (nuevo) 1 else 0)
        }

        // Cargar imagen desde la referencia almacenada en la BD (p. ej. "@drawable/nombre")
        com.example.carnotaturismo.util.ImageHelper
            .setImageFromRef(requireContext(), ruta.imagenMapa, binding.fotoMapa)
        binding.leyenda.text = requireContext().resolveString(ruta.leyenda)

        // Obtener los lugares asociados a esta ruta y mostrarlos
        model.getRutaConLugares(ruta.id).observe(viewLifecycleOwner) { rutaConLugares ->
            val textoLugares = rutaConLugares?.lugares?.joinToString(separator = "\n") { requireContext().resolveString(it.titulo) } ?: ""
            binding.lugaresVisitar.text = textoLugares
        }

        binding.descripcion.text = requireContext().resolveString(ruta.descripcion)
        binding.importante.text = requireContext().resolveString(ruta.importante)

        // Enlace imagen
        binding.fotoMapa.setOnClickListener {
            val webpage = args.ruta.imagenMapaEnlace
            val intent = Intent(Intent.ACTION_VIEW, webpage.toUri())
            startActivity(intent)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
