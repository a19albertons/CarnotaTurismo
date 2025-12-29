package com.example.carnotaturismo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carnotaturismo.adapters.VerTodosAdapter
import com.example.carnotaturismo.databinding.FragmentVerTodosBinding
import com.example.carnotaturismo.viewModel.TurismoAppModel
import kotlin.getValue

/**
 * Permite ver todos los lugares de un tipo en concreto.
 * Tambien soporta todos los tipos
 */
class VerTodosFragment : Fragment() {
    // bindings
    private var _binding: FragmentVerTodosBinding? = null
    private val binding get() = _binding!!

    // modelo
    private val model: TurismoAppModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVerTodosBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recuperamos del bundle
        val args = VerTodosFragmentArgs.fromBundle(requireArguments())
        val lista = args.listaLugaresMostrar

        // Recycler View
        val recyclerView = binding.RecyclerViewVertTodos

        // Configura el layout
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializar adapter con la lista que recibe
        val adapter = VerTodosAdapter(lista.toList())
        recyclerView.adapter = adapter

        // Texto tipo
        binding.tipo.text = args.tipo.toString()

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}