package com.example.foodapplication.data.datasource

import android.util.Log
import com.example.foodapplication.data.entity.CRUDResponse
import com.example.foodapplication.data.entity.SepetYemekler
import com.example.foodapplication.data.entity.Yemekler
import com.example.foodapplication.retrofit.ProductDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductDataSource(var pdao: ProductDao) {

    suspend fun search(searchText: String): List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext pdao.search(searchText).yemekler
    }

    suspend fun uploadProducts(): List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext pdao.uploadProducts().yemekler
    }

    suspend fun uploadCardProducts(kullanici_adi: String): List<SepetYemekler> =
        withContext(Dispatchers.IO) {
            return@withContext pdao.uploadCardProducts(kullanici_adi).sepet_yemekler
        }

    suspend fun addProductToCard(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        val response = pdao.addProductToCard(
            yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi
        )
        if (response.success == 0) {
            Log.e("Post Request", "Sepete ekleme başarısız $yemek_adi, $yemek_siparis_adet")
        } else
            Log.e("Post Request", "Sepete ekleme başarılı $yemek_adi, $yemek_siparis_adet")
    }

    suspend fun deleteProduct(sepet_yemek_id: Int,kullanici_adi: String)  {
        val response = pdao.deleteProduct(sepet_yemek_id ,kullanici_adi)
        if (response.success == 0) {
            Log.e("Post Request", "Sepetten silme başarısız $sepet_yemek_id")
        } else
            Log.e("Post Request", "Sepetten silme başarılı $sepet_yemek_id")
    }


}