package ru.partyshaker.partyshaker.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.partyshaker.partyshaker.ui.features.cocktails.api.CocktailsService
import ru.partyshaker.partyshaker.ui.features.cocktails.data.repository.CocktailsFilterRepositoryImpl
import ru.partyshaker.partyshaker.ui.features.cocktails.ui.filter.CocktailsFilterCategoriesViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesBaseUrl(): String = "https://partyshaker.online/api/"

    @Provides
    @Singleton
    fun provideRepository(cocktailsService: CocktailsService): CocktailsFilterRepositoryImpl =
        CocktailsFilterRepositoryImpl(cocktailsService)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(providesBaseUrl())
        .build()

    @Provides
    @Singleton
    fun provideCocktailsService(retrofit: Retrofit): CocktailsService =
        retrofit.create(CocktailsService::class.java)
}