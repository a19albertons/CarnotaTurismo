package com.example.carnotaturismo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.carnotaturismo.R
import com.example.carnotaturismo.databinding.FragmentSobreBinding
import com.example.carnotaturismo.viewModel.TurismoAppModel

/**
 * Clase que representa el fragmento Sobre
 */
class SobreFragment : Fragment() {
    // bindings
    private var _binding: FragmentSobreBinding? = null
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
        _binding = FragmentSobreBinding.inflate(inflater, container, false)
        val view = binding.root

        // Mostrar versión

        binding.versionText.text = getString(R.string.version_label) + "1.0.5"

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
