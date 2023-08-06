package com.example.foodapplication.data.repository

import com.example.foodapplication.data.datasource.ProductDataSource
import com.example.foodapplication.data.entity.CRUDResponse
import com.example.foodapplication.data.entity.SepetYemekler
import com.example.foodapplication.data.entity.Yemekler


class ProductRepository(var pds: ProductDataSource) {

    suspend fun uploadProducts(): List<Yemekler> = pds.uploadProducts()

    suspend fun deleteProduct(sepet_yemek_id:Int,kullanici_adi: String) = pds.deleteProduct(sepet_yemek_id,kullanici_adi)

    suspend fun search(searchText: String): List<Yemekler> = pds.search(searchText)

    suspend fun addProductToCard(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) = pds.addProductToCard(
        yemek_adi,
        yemek_resim_adi,
        yemek_fiyat,
        yemek_siparis_adet,
        kullanici_adi
    )

    suspend fun uploadCardProducts(kullanici_adi:String): List<SepetYemekler> = pds.uploadCardProducts(kullanici_adi)

}
