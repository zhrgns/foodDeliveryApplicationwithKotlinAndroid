package com.example.foodapplication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapplication.data.entity.CRUDResponse
import com.example.foodapplication.data.entity.Yemekler
import com.example.foodapplication.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(var prepo: ProductRepository) : ViewModel() {

    var yemek_siparis_adet: MutableLiveData<String> = MutableLiveData("1")

    fun addProductToCard(yemeklerObject: Yemekler, yemek_siparis_adet: String) {
        val yemek_fiyat: Int = yemeklerObject.yemek_fiyat.toInt() * yemek_siparis_adet.toInt()

        CoroutineScope(Dispatchers.Main).launch {
            prepo.addProductToCard(
                yemeklerObject.yemek_adi,
                yemeklerObject.yemek_resim_adi,
                yemek_fiyat,
                yemek_siparis_adet.toInt(),
                "zehragunes"
            )
        }

    }
}
