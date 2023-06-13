package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import androidx.recyclerview.widget.DiffUtil
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail

class CocktailItemDiffCallback : DiffUtil.ItemCallback<ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail>() {
    override fun areItemsTheSame(oldItem: ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail, newItem: ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail, newItem: ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail): Boolean {
        return oldItem == newItem
    }
}