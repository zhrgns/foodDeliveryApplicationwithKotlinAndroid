package com.example.foodapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodapplication.R
import com.example.foodapplication.databinding.FragmentCardBinding
import com.example.foodapplication.ui.adapter.CardProductAdapter
import com.example.foodapplication.ui.adapter.YemeklerAdapter
import com.example.foodapplication.ui.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private lateinit var viewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false)
        binding.cardFragment = this
        binding.toolbarCard.title = "Sepetim"

        viewModel.sepetYemeklerList.observe(viewLifecycleOwner) {
            val adapter = CardProductAdapter(requireContext(), it, viewModel)
            binding.cardYemeklerAdapter = adapter
            viewModel.calculateTotal()
        }

        viewModel.totalCount.observe(viewLifecycleOwner) { total ->
            binding.totalCount = total
        }

        // Call uploadCardProducts here to ensure data is loaded before view creation
        uploadCardProducts(getString(R.string.kullanici_adi))

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CardViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun uploadCardProducts(kullanici_adi: String) {
        viewModel.uploadCardProducts(kullanici_adi)
    }

}