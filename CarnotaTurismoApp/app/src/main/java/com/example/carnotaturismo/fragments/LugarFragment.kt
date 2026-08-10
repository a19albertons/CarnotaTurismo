package com.example.carnotaturismo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carnotaturismo.adapters.LugarAdapter
import com.example.carnotaturismo.databinding.FragmentLugarBinding
import com.example.carnotaturismo.viewModel.TurismoAppModel

/**
 * Fragmento que representa a un lugar/ubicacion
 */
class LugarFragment : Fragment() {
    // bindings
    private var _binding: FragmentLugarBinding? = null
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
        _binding = FragmentLugarBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recycler View
        // Configurar el RecyclerView
        val recyclerView = binding.lugarAdapter

        // Declara el layout
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializar adapter con lista vacía y observar cambios en LiveData
        val listaLugares = model.lugares.value ?: emptyList()
        val adapter =
            LugarAdapter(listaLugares) { lugar ->
                model.setFavoritoLugar(lugar.id, if (lugar.favorito) 1 else 0)
            }
        recyclerView.adapter = adapter

        // Observar cambios en LiveData y actualizar el adapter
        model.lugares.observe(viewLifecycleOwner) { lista ->
            adapter.setData(lista)
        }

        return view
    }
}
