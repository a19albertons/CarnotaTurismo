package com.example.carnotaturismo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carnotaturismo.adapters.ItinerarioListaAdapter
import com.example.carnotaturismo.databinding.FragmentItinerarioListaBinding
import com.example.carnotaturismo.viewModel.TurismoAppModel
import kotlin.getValue

/**
 * Fragmento que representa la lista de itinerario
 */
class ItinerarioListaFragment : Fragment() {
    // bindings
    private var _binding: FragmentItinerarioListaBinding? = null
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
        _binding = FragmentItinerarioListaBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recycler View
        val recyclerView = binding.RecyclerViewItinerarioLista
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // Inicializar adapter con lista vacía y observar cambios en LiveData
        val listaRutas = model.rutas.value ?: emptyList()
        val adapter =
            ItinerarioListaAdapter(
                itineraios = listaRutas,
            ) { ruta ->
                model.setFavoritoRuta(ruta.id, if (ruta.favorito) 1 else 0)
            }

        recyclerView.adapter = adapter

        // Observar cambios en LiveData y actualizar el adapter
        model.rutas.observe(viewLifecycleOwner) { lista ->
            adapter.setData(lista)
        }

        return view
    }
}
