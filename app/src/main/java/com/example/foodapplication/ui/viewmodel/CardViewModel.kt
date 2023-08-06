package com.example.foodapplication.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapplication.data.entity.SepetYemekler
import com.example.foodapplication.data.entity.Yemekler
import com.example.foodapplication.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(var prepo: ProductRepository) : ViewModel() {
    var sepetYemeklerList = MutableLiveData<List<SepetYemekler>>()

    val totalCount: MutableLiveData<String> = MutableLiveData("0")

    @SuppressLint("NullSafeMutableLiveData")
    fun deleteProduct(sepet_yemek_id: Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val sepetYemekler = sepetYemeklerList.value
            if (sepetYemekler != null && sepetYemekler.size == 1) {
                sepetYemeklerList.value = emptyList()
            } else {
                prepo.deleteProduct(sepet_yemek_id, kullanici_adi)
                uploadCardProducts(kullanici_adi)
            }
        }
    }

    fun uploadCardProducts(kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            sepetYemeklerList.value = prepo.uploadCardProducts(kullanici_adi) }
    }

    fun calculateTotal() {
        var totalPrice = 0
        sepetYemeklerList.value?.forEach { sepetYemek ->
            totalPrice += sepetYemek.yemek_fiyat.toInt()
        }
        Log.e("Calculate", "$totalPrice")
        totalCount.value = totalPrice.toString()
    }
}


