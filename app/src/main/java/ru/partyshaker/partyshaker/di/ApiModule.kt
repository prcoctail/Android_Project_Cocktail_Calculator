package ru.partyshaker.partyshaker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.partyshaker.partyshaker.ui.features.cocktails.api.CocktailsService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesBaseUrl(): String = "https://partyshaker.online/api/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideCocktailsService(retrofit: Retrofit): CocktailsService =
        retrofit.create(CocktailsService::class.java)
}