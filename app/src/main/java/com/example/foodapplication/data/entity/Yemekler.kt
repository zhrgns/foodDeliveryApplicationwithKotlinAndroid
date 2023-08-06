package com.example.foodapplication.data.entity

import java.io.Serializable

data class Yemekler(
    var yemek_id: String,
    var yemek_adi: String,
    var yemek_resim_adi: String,
    var yemek_fiyat: String,

    ) : Serializable

//{
//    "yemek_id": "11",
//    "yemek_adi": "Pizza",
//    "yemek_resim_adi": "pizza.png",
//    "yemek_fiyat": "45"
//}