package com.example.foodapplication.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapplication.R
import com.example.foodapplication.data.entity.Yemekler
import com.example.foodapplication.databinding.MainCardDesignBinding
import com.example.foodapplication.ui.fragment.HomepageFragmentDirections
import com.example.foodapplication.ui.viewmodel.HomepageViewModel

class YemeklerAdapter(
    var mContext: Context, var yemeklerList: List<Yemekler>, var viewModel: HomepageViewModel
) : RecyclerView.Adapter<YemeklerAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(var design: MainCardDesignBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding: MainCardDesignBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.main_card_design,
            parent,
            false
        )

        return CardDesignHolder(binding)
    }

    //belirli bir pozisyondaki öğeyi view holder a bağlıyor
    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val yemekler = yemeklerList.get(position)
        val t = holder.design

        t.yemeklerObject = yemekler

        val urlImage = "http://kasimadalan.pe.hu/yemekler/resimler/${yemekler.yemek_resim_adi}"
        getImage(urlImage, t.imageViewProduct)


        t.cardViewProduct.setOnClickListener {
            val nav = HomepageFragmentDirections.actionHomepageToDetail(yemekler = yemekler)
            Navigation.findNavController(it).navigate(nav)
        }

        t.buttonAddMore.setOnClickListener {
            addProductToCard(yemekler, "1")
        }
    }

    fun addProductToCard(yemeklerObject: Yemekler, yemek_siparis_adet: String) {
        viewModel.addProductToCard(yemeklerObject, yemek_siparis_adet.toInt())
    }

    fun getImage(urlImage: String, image_view: ImageView) {
        Glide.with(mContext).load(urlImage).placeholder(R.drawable.product_image).into(image_view)
    }

    // listedeki toplam öğe sayısını döndürüyor
    override fun getItemCount(): Int {
        return yemeklerList.size
    }


}