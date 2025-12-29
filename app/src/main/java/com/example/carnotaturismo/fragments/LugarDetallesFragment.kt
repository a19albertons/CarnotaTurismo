package com.example.carnotaturismo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.carnotaturismo.databinding.FragmentLugarDetallesBinding
import com.example.carnotaturismo.viewModel.TurismoAppModel

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
        binding.titulo.text = lugar.titulo
        //val ctx = holder.itemView.context
        //val imgRef = currentItem.imagen

        // extrae "drawable" y "mi_imagen"
        //val (type, name) = imgRef.removePrefix("@").split('/', limit = 2)
        //val resId = ctx.resources.getIdentifier(name, type, ctx.packageName)
        //if (resId != 0) {
        //    holder.imagen.setImageResource(resId)
        //} else {
        //    holder.imagen.setImageResource(R.drawable.error_imagen) // fallback
        //}
        binding.leyenda.text = lugar.leyenda
        binding.descripcion.text = lugar.descripcion
        binding.importante.text = lugar.importante




        return view
    }


}