package com.example.foodapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapplication.R
import com.example.foodapplication.data.entity.SepetYemekler
import com.example.foodapplication.data.entity.Yemekler
import com.example.foodapplication.databinding.CardCardDesignBinding
import com.example.foodapplication.ui.viewmodel.CardViewModel
import com.google.android.material.snackbar.Snackbar


class CardProductAdapter(
    var mContext: Context, var sepetYemeklerList: List<SepetYemekler>, var viewModel: CardViewModel
) : RecyclerView.Adapter<CardProductAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(var design: CardCardDesignBinding) :
        RecyclerView.ViewHolder(design.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding: CardCardDesignBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext), R.layout.card_card_design, parent, false
        )
        return CardDesignHolder(binding)
    }

    //belirli bir pozisyondaki öğeyi view holder a bağlıyor
    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val sepetYemekler = sepetYemeklerList.get(position)
        val t = holder.design

        t.sepetYemeklerObject = sepetYemekler

        val urlImage = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemekler.yemek_resim_adi}"
        getImage(urlImage, t.imageViewProduct)

        t.imageViewRemove.setOnClickListener {
            Snackbar.make(it, "${sepetYemekler.yemek_adi} silinsin mi ?", Snackbar.LENGTH_SHORT)
                .setAction("EVET") {
                    deleteProduct(sepetYemekler.sepet_yemek_id, sepetYemekler.kullanici_adi)
                }.show()
        }
    }

    fun deleteProduct(sepet_yemek_id: String, kullanici_adi: String) {
        viewModel.deleteProduct(sepet_yemek_id.toInt(), kullanici_adi)
    }

    fun getImage(urlImage: String, image_view: ImageView) {
        Glide.with(mContext).load(urlImage).placeholder(R.drawable.product_image).into(image_view)
    }

    // listedeki toplam öğe sayısını döndürüyor
    override fun getItemCount(): Int {
        return sepetYemeklerList.size
    }

}