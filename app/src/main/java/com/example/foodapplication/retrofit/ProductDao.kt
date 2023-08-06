package com.example.foodapplication.retrofit

import com.example.foodapplication.data.entity.CRUDResponse
import com.example.foodapplication.data.entity.SepetYemeklerResponse
import com.example.foodapplication.data.entity.YemeklerResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun uploadProducts(): YemeklerResponse

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun uploadCardProducts(@Field("kullanici_adi") kullanici_adi:String):SepetYemeklerResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addProductToCard(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): CRUDResponse

    @POST("yemekler/tumYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun search(@Field("yemek_adi") yemek_adi: String): YemeklerResponse

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteProduct(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                              @Field("kullanici_adi") kullanici_adi: String) : CRUDResponse

}