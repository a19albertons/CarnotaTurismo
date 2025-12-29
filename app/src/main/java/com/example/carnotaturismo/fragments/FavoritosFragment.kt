package com.example.carnotaturismo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carnotaturismo.adapters.FavoritosLugarAdapter
import com.example.carnotaturismo.adapters.FavoritosRutaAdapter
import com.example.carnotaturismo.databinding.FragmentFavoritosBinding
import com.example.carnotaturismo.viewModel.TurismoAppModel
import kotlin.getValue

/**
 * Clase que representa el fragmento Favoritos
 */
class FavoritosFragment : Fragment() {
    // bindings
    private var _binding: FragmentFavoritosBinding? = null
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
        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        val view = binding.root

        // RecyclerView
        // Lugares
        val recyclerViewLugar = binding.RecyclerViewFavoritosLugar
        recyclerViewLugar.layoutManager = LinearLayoutManager(requireContext())
        val lugar = model.obtenerLugaresFavoritos().value?: emptyList()
        val adapterLugar = FavoritosLugarAdapter(lugar)
        recyclerViewLugar.adapter = adapterLugar

        // Ruta
        val recyclerViewRuta = binding.RecyclerViewFavoritosRutas
        recyclerViewRuta.layoutManager = LinearLayoutManager(requireContext())
        val ruta = model.obtenerRutasFavoritas().value?: emptyList()
        val adapterRuta = FavoritosRutaAdapter(ruta)
        recyclerViewRuta.adapter = adapterRuta

        // Observar solo los favoritos y actualizar el adapter
        model.obtenerLugaresFavoritos().observe(viewLifecycleOwner) { lista ->
            adapterLugar.setDataLugar(lista)
        }
        model.obtenerRutasFavoritas().observe(viewLifecycleOwner) { lista ->
            adapterRuta.setDataRuta(lista)
        }





        return view
    }


}