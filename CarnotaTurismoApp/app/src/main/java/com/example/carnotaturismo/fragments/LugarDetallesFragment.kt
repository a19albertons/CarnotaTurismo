package com.example.carnotaturismo.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.carnotaturismo.R
import com.example.carnotaturismo.databinding.FragmentLugarDetallesBinding
import com.example.carnotaturismo.viewModel.TurismoAppModel
import com.example.carnotaturismo.util.resolveString

/**
 * Fragmento que representa lugar detalles
 */
class LugarDetallesFragment : Fragment() {
    // bindings
    private var _binding: FragmentLugarDetallesBinding? = null
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
        _binding = FragmentLugarDetallesBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recuperar Bundle
        val args = LugarDetallesFragmentArgs.fromBundle(requireArguments())
        val lugar = args.lugar

        // Actualizar UI
        binding.titulo.text = requireContext().resolveString(lugar.titulo)
        // Mostrar icono favorito segun BD
        binding.ivFavorito.setImageResource(if (lugar.favorito) R.drawable.favorito else R.drawable.no_favorito)

        // Toggle favorito al pulsar (optimistic UI + persistir via ViewModel)
        binding.ivFavorito.setOnClickListener {
            // Invertir estado
            val nuevo = !lugar.favorito
            lugar.favorito = nuevo
            // Actualizar icono
            binding.ivFavorito.setImageResource(if (nuevo) R.drawable.favorito else R.drawable.no_favorito)
            // Persistir cambio via ViewModel
            model.setFavoritoLugar(lugar.id, if (nuevo) 1 else 0)
        }

        // Cargar imagen desde la referencia almacenada en la BD (p. ej. "@drawable/nombre")
        com.example.carnotaturismo.util.ImageHelper.setImageFromRef(requireContext(), lugar.imagenMapa, binding.fotoMapa)
        binding.leyenda.text = requireContext().resolveString(lugar.leyenda)
        binding.descripcion.text = requireContext().resolveString(lugar.descripcion)
        binding.importante.text = requireContext().resolveString(lugar.importante)

        // Enlace imagen
        binding.fotoMapa.setOnClickListener {
            val webpage = args.lugar.imagenMapaEnlace
            val intent = Intent(Intent.ACTION_VIEW, webpage.toUri())
            startActivity(intent)
        }





        return view
    }


}