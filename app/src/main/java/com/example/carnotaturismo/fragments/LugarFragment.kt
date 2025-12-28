package com.example.carnotaturismo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carnotaturismo.R
import com.example.carnotaturismo.adapters.LugarAdapter
import com.example.carnotaturismo.adapters.VerTodosAdapter
import com.example.carnotaturismo.databinding.FragmentLugarBinding
import com.example.carnotaturismo.model.TurismoAppModel


class LugarFragment : Fragment() {
    private var _binding: FragmentLugarBinding? = null
    private val binding get() = _binding!!

    private val model: TurismoAppModel by viewModels() {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLugarBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recycler View
        // Configurar el RecyclerView
        val recyclerView = binding.lugarAdapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val listaLugares = model.lugares.value ?: emptyList()
        val adapter = LugarAdapter(listaLugares)
        recyclerView.adapter = adapter
        model.lugares.observe(viewLifecycleOwner) { lista ->
            adapter.setData(lista)
        }

        return view
    }


}