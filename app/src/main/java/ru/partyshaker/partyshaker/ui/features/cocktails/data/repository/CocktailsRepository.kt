package ru.partyshaker.partyshaker.ui.features.cocktails.data.repository;

interface CocktailsRepository {
    suspend fun getAllCocktails()
}