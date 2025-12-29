package com.example.carnotaturismo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.carnotaturismo.adapters.InicioVerticalAdapter
import com.example.carnotaturismo.databinding.FragmentInicioBinding
import com.example.carnotaturismo.model.TipoUbicacion
import com.example.carnotaturismo.viewModel.TurismoAppModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

/**
 * Fragmento inicio
 */
class InicioFragment : Fragment() {

    // bindings
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    // model
    private val model: TurismoAppModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recycler View Vertical
        // Configurar el RecyclerView vertical
        val recyclerViewVertical = binding.RecyclerViewVertical
        recyclerViewVertical.layoutManager = LinearLayoutManager(requireContext())

        // Inicializar adapter con lista vacía y observar cambios en LiveData
        val adapter = InicioVerticalAdapter(TipoUbicacion.entries.toTypedArray(), emptyList()) { lista ->
            // Navegar desde el fragment (usando Safe Args) para mantener el BottomNavigationView sincronizado
            // La opción que intenta aplicar democracia
            val array = lista.toTypedArray()
            val action = InicioFragmentDirections.actionInicioFragmentToVerTodosFragment(array, lista[0].tipo)
            findNavController().navigate(action)
        }
        recyclerViewVertical.adapter = adapter

        // Observar cambios en LiveData y actualizar el adapter
        model.lugares.observe(viewLifecycleOwner) { lista ->
            adapter.setData(lista)
        }



        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}