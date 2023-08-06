package com.example.foodapplication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapplication.data.entity.Yemekler
import com.example.foodapplication.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(var prepo: ProductRepository) : ViewModel() {

    var yemeklerList = MutableLiveData<List<Yemekler>>()

    init {
        uploadProducts()
    }

    fun uploadProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerList.value = prepo.uploadProducts()
        }
    }

    fun addProductToCard(yemeklerObject: Yemekler, yemek_siparis_adet: Int) {

        val yemek_fiyat: Int = yemeklerObject.yemek_fiyat.toInt() * yemek_siparis_adet

        CoroutineScope(Dispatchers.Main).launch {
            prepo.addProductToCard(
                yemeklerObject.yemek_adi,
                yemeklerObject.yemek_resim_adi,
                yemek_fiyat,
                yemek_siparis_adet,
                "zehragunes"
            )
        }

    }

    fun search(searchText: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                yemeklerList.value = prepo.search(searchText)
            } catch (e: Exception) {
            }
        }
    }
}
