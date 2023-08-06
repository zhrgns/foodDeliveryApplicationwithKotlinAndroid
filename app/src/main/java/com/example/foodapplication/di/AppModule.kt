package com.example.foodapplication.di

import com.example.foodapplication.data.datasource.ProductDataSource
import com.example.foodapplication.data.repository.ProductRepository
import com.example.foodapplication.retrofit.ApiUtils
import com.example.foodapplication.retrofit.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideProductRepository(pds:ProductDataSource) : ProductRepository {
        return ProductRepository(pds)
    }

    @Provides
    @Singleton
    fun provideProductDataSource(pdao:ProductDao) : ProductDataSource {
        return ProductDataSource(pdao)
    }

    @Provides
    @Singleton
    fun provideProductDao() : ProductDao {
        return ApiUtils.getProductDao()
    }
}