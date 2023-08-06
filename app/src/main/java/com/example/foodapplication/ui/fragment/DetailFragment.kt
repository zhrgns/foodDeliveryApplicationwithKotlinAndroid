package com.example.foodapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodapplication.R
import com.example.foodapplication.data.entity.CRUDResponse
import com.example.foodapplication.data.entity.Yemekler
import com.example.foodapplication.databinding.FragmentDetailBinding
import com.example.foodapplication.ui.viewmodel.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        //görsel nesneyi şişirdik
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.detailFragmentObject = this
        val bundle: DetailFragmentArgs by navArgs()
        val yemekler = bundle.yemekler
        binding.yemeklerObject = yemekler

        //image loading
        val urlImage = "http://kasimadalan.pe.hu/yemekler/resimler/${yemekler.yemek_resim_adi}"
        getImage(urlImage, binding.imageViewProductDetail)


        binding.countProduct = "1"
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun addProductToCard(yemeklerObject: Yemekler, adet:String) {
        viewModel.addProductToCard(yemeklerObject, adet)
    }

    fun onIncrementButtonClick(productCount:String) {
        binding.countProduct = productCount.toInt().plus(1).toString()
    }

    fun getImage(urlImage: String, image_view: ImageView) {
        Glide.with(requireContext())
            .load(urlImage)
            .placeholder(R.drawable.product_image)
            .into(image_view)
    }
}